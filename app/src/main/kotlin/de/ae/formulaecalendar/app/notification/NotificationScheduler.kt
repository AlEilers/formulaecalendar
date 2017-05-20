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
import com.jakewharton.threetenabp.AndroidThreeTen
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.raceStart
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by aeilers on 18.02.2017.
 */
class NotificationScheduler : BroadcastReceiver() {
    val pref_notification = "pref_notification"

    override fun onReceive(context: Context, intent: Intent) {
        AndroidThreeTen.init(context)
        scheduleNotifications(context, RemoteStore.getCurrentRaceCalendar())
    }

    fun scheduleNotifications(context: Context, obs: Observable<RaceCalendarData?>) {
        AndroidThreeTen.init(context)


        obs.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(object : Observer<RaceCalendarData?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable) {
                        Log.w("NotificationReceiver", "Cannot schedule notification: ${t.message}")
                    }

                    override fun onNext(raceCalendarData: RaceCalendarData?) {
                        raceCalendarData?.calendarData?.let { schedule(context, it) } ?:
                                Log.w("NotificationReceiver", "Cannot schedule notification: RaceCalendarData is null")
                    }
                })
    }

    private fun schedule(context: Context, races: List<CalendarDatum>) {

        val offset = calculateOffset(context)
        if (offset < 0) return

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

        races.filter { it.raceStart.toEpochSecond() * 1000 > System.currentTimeMillis() + offset }
                .forEach {
                    val intent = createIntent(context, it)
                    val pendingIntent = PendingIntent.getService(context, Integer.parseInt(it.sequence), intent, 0)
                    val raceStartMilis = it.raceStart.toEpochSecond() * 1000
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, raceStartMilis - offset, pendingIntent)
                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, raceStartMilis - offset, pendingIntent)
                    }
                }
    }

    private fun calculateOffset(context: Context): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val value = prefs.getString(pref_notification, "-1")
        val offset = value.toInt()
        return offset * 60 * 100
    }

    private fun createIntent(context: Context, race: CalendarDatum): Intent {
        val intent = Intent(context, NotificationService::class.java)
        intent.putExtra("NOTIFICATION_TITLE", race.raceName)
        intent.putExtra("NOTIFICATION_CONTENT", createContent(context, race))
        intent.putExtra("NOTIFICATION_ID", race.hashCode())
        return intent
    }

    private fun createContent(context: Context, race: CalendarDatum): String {
        val format = context.getString(R.string.format_date) + ' ' + context.getString(R.string.format_time)
        val zdt = race.raceStart.withZoneSameInstant(ZoneId.systemDefault())
        val time = zdt.format(DateTimeFormatter.ofPattern(format))
        return String.format(context.getString(R.string.noti_content), time)
    }

}