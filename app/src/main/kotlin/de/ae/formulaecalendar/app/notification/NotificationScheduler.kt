package de.ae.formulaecalendar.app.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.DATE_FORMAT
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.isDateInFuture
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by aeilers on 18.02.2017.
 */
class NotificationScheduler : BroadcastReceiver() {
    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    override fun onReceive(context: Context, intent: Intent) {
        scheduleNotifications(context, RemoteStore.getCurrentRaceCalendar())
    }

    fun scheduleNotifications(context: Context, obs: Observable<RaceCalendarData?>) {

        obs.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(object : Observer<RaceCalendarData?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable) {
                        Log.w("NotificationScheduler", "Cannot schedule notification: ${t.message}")
                    }

                    override fun onNext(raceCalendarData: RaceCalendarData?) {
                        raceCalendarData?.calendarData?.let { schedule(context, it) }
                                ?: Log.w("NotificationScheduler", "Cannot schedule notification: RaceCalendarData is null")
                    }
                })
    }

    private fun schedule(context: Context, races: List<CalendarDatum>) {
        if (!isNotificationsEnabled(context)) return

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

        races.filter { race -> race.raceDate?.let { isDateInFuture(it) } == true }
                .forEach {
                    val intent = createIntent(context, it)
                    val pendingIntent = PendingIntent.getService(
                            context,
                            Integer.parseInt(it.sequence?: ""),
                            intent,
                            0
                    )
                    val raceStartMilis = it.raceDate?.time ?: 0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, raceStartMilis, pendingIntent)
                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, raceStartMilis, pendingIntent)
                    }
                }
    }

    private fun isNotificationsEnabled(context: Context): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val preferenceKey = context.getString(R.string.pref_notification_enabled)
        return prefs.getBoolean(preferenceKey, true)
    }

    private fun createIntent(context: Context, race: CalendarDatum) =
            Intent(context, NotificationService::class.java)
                    .putExtra("NOTIFICATION_TITLE", race.raceName)
                    .putExtra("NOTIFICATION_CONTENT", createContent(context, race))
                    .putExtra("NOTIFICATION_ID", race.raceId)

    private fun createContent(context: Context, race: CalendarDatum): String {
        val time = simpleDateFormat.format(race.raceDate)
        return String.format(context.getString(R.string.noti_content), time)
    }

}