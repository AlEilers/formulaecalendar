package de.ae.formulaecalendar.view.settings

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.calendar.MyCalendarProvider
import de.ae.formulaecalendar.notification.NotificationReceiver
import de.ae.formulaecalendar.remote.RemoteStore

/**
 * Created by aeilers on 18.02.2017.
 */
class MyPreferenceFragment : PreferenceFragment(), ActivityCompat.OnRequestPermissionsResultCallback {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private val calendarChangeEvent = "calendar_settings_changed"
    private val calendarPermissionParam = "permission_granted"
    private val calendarRaceEnabledParam = "race_enabled"
    private val calendarQualiEnabledParam = "quali_enabled"
    private val calendarBundle = Bundle()

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

        findPreference(notification).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            notificationChanged = true
            notificationBundle.putInt(notificationValueParam, (newValue as String).toInt())
            true
        }

        findPreference(calendar).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            requestPermission()
            calendarBundle.putBoolean(calendarRaceEnabledParam, newValue as Boolean)
            true
        }

        findPreference(quali).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            requestPermission()
            calendarBundle.putBoolean(calendarQualiEnabledParam, newValue as Boolean)
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
                    calendarBundle.putBoolean(calendarPermissionParam, true)
                } else {
                    (findPreference(calendar) as SwitchPreference).isChecked = false
                    Log.w("MyPreferenceFragment", "Permission Calendar not granted")

                    //Firebase
                    calendarBundle.putBoolean(calendarPermissionParam, false)
                }
            }

        // Add other Requests
        }
    }

    override fun onStop() {
        if (calendarChanged) {
            MyCalendarProvider(this.activity).manageCalendar(this.activity, RemoteStore.getCurrentRaceCalendar())
            calendarChanged = false

            //Firebase
            mFirebaseAnalytics?.logEvent(calendarChangeEvent, calendarBundle);
        }
        if (notificationChanged) {
            NotificationReceiver().scheduleNotifications(activity, RemoteStore.getCurrentRaceCalendar())
            notificationChanged = false

            //Firebase
            mFirebaseAnalytics?.logEvent(notificationChangeEvent, notificationBundle);
        }
        super.onStop()
    }
}