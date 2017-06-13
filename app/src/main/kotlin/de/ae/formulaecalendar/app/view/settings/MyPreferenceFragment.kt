package de.ae.formulaecalendar.app.view.settings

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.calendar.MyCalendarProvider
import de.ae.formulaecalendar.app.notification.NotificationScheduler
import de.ae.formulaecalendar.formulaerest.RemoteStore

/**
 * Created by aeilers on 18.02.2017.
 */
class MyPreferenceFragment : PreferenceFragment(), ActivityCompat.OnRequestPermissionsResultCallback {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private val calendarChangeEvent = "calendar_changed"
    private val calendarChangeParam = FirebaseAnalytics.Param.VALUE
    private val calendarBundle = Bundle()

    private val calendarPermissionEvent = "calendar_permission_granted"
    private val calendarPermissionParam = FirebaseAnalytics.Param.VALUE
    private val permissionBundle = Bundle()

    private val notificationChangeEvent = "notification_changed"
    private val notificationValueParam = FirebaseAnalytics.Param.VALUE
    private val notificationBundle = Bundle()

    private val MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1
    private val notification = "pref_notification"
    private val calendar = "pref_calendar"
    private val quali = "pref_quali"

    private var notificationChanged = false
    private var calendarChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity)

        findPreference(notification).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            notificationChanged = true
            notificationBundle.putInt(notificationValueParam, (newValue as String).toInt())
            true
        }

        findPreference(calendar).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            requestPermission()
            calendarBundle.putBoolean(calendarChangeParam, newValue as Boolean)
            true
        }

        findPreference(quali).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, _ ->
            requestPermission()
            true
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.WRITE_CALENDAR),
                MY_PERMISSIONS_REQUEST_WRITE_CALENDAR)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_CALENDAR -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    calendarChanged = true

                    //Firebase
                    permissionBundle.putBoolean(calendarPermissionParam, true)
                } else {
                    (findPreference(calendar) as SwitchPreference).isChecked = false
                    Log.w("MyPreferenceFragment", "Permission Calendar not granted")

                    //Firebase
                    permissionBundle.putBoolean(calendarPermissionParam, false)
                }

                mFirebaseAnalytics?.logEvent(calendarPermissionEvent, permissionBundle)
            }

        // Add other Requests
        }
    }

    override fun onStop() {
        if (calendarChanged) {
            MyCalendarProvider(this.activity.applicationContext).manageCalendar(this.activity.applicationContext, RemoteStore.getCurrentRaceCalendar())
            calendarChanged = false

            //Firebase
            mFirebaseAnalytics?.logEvent(calendarChangeEvent, calendarBundle);
        }
        if (notificationChanged) {
            NotificationScheduler().scheduleNotifications(activity, RemoteStore.getCurrentRaceCalendar())
            notificationChanged = false

            //Firebase
            mFirebaseAnalytics?.logEvent(notificationChangeEvent, notificationBundle)
        }
        super.onStop()
    }
}