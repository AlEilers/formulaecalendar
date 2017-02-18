package de.ae.formulaecalendar.view.settings

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.support.v4.app.ActivityCompat
import android.util.Log
import de.ae.formulaecalendar.R

/**
 * Created by alexa on 18.02.2017.
 */
class MyPreferenceFragment : PreferenceFragment(), ActivityCompat.OnRequestPermissionsResultCallback {
    private val MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1
    private val notification = "pref_notification"
    private val calendar = "pref_calendar"
    private val quali = "pref_quali"

    private var notificationChanged = false
    private var calendarChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        findPreference(notification).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            notificationChanged = true
            true
        }

        findPreference(calendar).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            requestPermission()
            true
        }

        findPreference(quali).onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
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
                } else {
                    (findPreference(calendar) as SwitchPreference).isChecked = false
                    Log.w(MyPreferenceFragment::class.simpleName, "Permission Calendar not granted")
                }
            }

        // Add other Requests
        }
    }

    override fun onStop() {
        //TODO Calendar and Notification
        if (calendarChanged) {
            //MyCalendarProvider(this).manageCalendar(RemoteStore.Instance.getCurrentRaceCalendar())
            calendarChanged = false
        }
        if (notificationChanged) {
            //NotificationReceiver.scheduleNotifications(getApplicationContext(), RemoteStore.Instance.getCurrentRaceCalendar())
            notificationChanged = false
        }
        super.onStop()
    }
}