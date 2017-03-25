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
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by aeilers on 18.02.2017.
 */
class NotificationReceiver : BroadcastReceiver() {
    val pref_notification = "pref_notification"

    override fun onReceive(context: Context, intent: Intent) {
        scheduleNotifications(context, RemoteStore.getCurrentRaceCalendar())
    }

    fun scheduleNotifications(context: Context, obs: Observable<RaceCalendarData?>) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val value = prefs.getString(pref_notification, "-1")
        val offset = value.toInt()
        if (offset == -1) { //notifications disabled
            return
        }
        val finoffset = offset * 60 * 1000 //min to millis

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
                        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
                        val data = raceCalendarData?.calendarData
                        if (data != null) {
                            for (race in data) {
                                val raceStart = race.raceStart.toEpochSecond() * 1000
                                if (raceStart > System.currentTimeMillis() + finoffset) {
                                    val intent = Intent(context, NotificationService::class.java)
                                    intent.putExtra("race", race)
                                    val pendingIntent = PendingIntent.getService(context, Integer.parseInt(race.sequence), intent, 0)
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, raceStart - finoffset, pendingIntent)
                                    } else {
                                        alarmManager.set(AlarmManager.RTC_WAKEUP, raceStart - finoffset, pendingIntent)
                                    }
                                }
                            }
                        } else {
                            Log.w("NotificationReceiver", "Cannot schedule notification: RaceCalendarData is null")
                        }
                    }
                })
        return
    }

}