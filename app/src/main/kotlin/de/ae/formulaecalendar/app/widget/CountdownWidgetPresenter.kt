package de.ae.formulaecalendar.app.widget

import android.util.Log
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by aeilers on 18.02.2017.
 */
class CountdownWidgetPresenter constructor(val view: CountdownWidgetView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {
    private val HOUR_IN_MILLIS = (1000 * 60 * 60).toLong()
    private val DAY_IN_MILLIS = (1000 * 60 * 60 * 24).toLong()

    constructor(view: CountdownWidgetView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadWidget(previous: Long?) {
        val currentTime = System.currentTimeMillis()
        if (previous != null && previous > currentTime) {
            val countdown = millisToCountdown(previous)
            view.setContent(null, countdown, null, true)
        } else {
            model.getCurrentRaceCalendar()
                    .subscribeOn(subscriber)
                    .observeOn(observer)
                    .subscribe(object : Observer<RaceCalendarData?> {
                        override fun onSubscribe(d: Disposable?) {

                        }

                        override fun onComplete() {
                            //do nothing
                        }

                        override fun onError(t: Throwable) {
                            val title = ""
                            val countdown = view.getContext()?.getString(R.string.widget_loading)
                            val date = ""
                            view.setContent(title, countdown, date, false)
                            Log.w("CountdownWidgetPresente", "Cannot load view: ${t.message}")
                        }

                        override fun onNext(raceCalendarData: RaceCalendarData?) {
                            val next = raceCalendarData?.nextRace
                            if (next == null) {
                                val title = ""
                                val countdown = view.getContext()?.getString(R.string.widget_no_next)
                                val date = ""
                                view.setContent(title, countdown, date, false)
                                view.saveNext(-1)
                            } else {
                                val title = next.raceName
                                val countdown = millisToCountdown(next.raceStart.toEpochSecond() * 1000)
                                val date = dateToString(next.raceStart)
                                view.setContent(title, countdown, date, true)
                                view.saveNext(next.raceStart.toEpochSecond() * 1000)
                            }
                        }
                    })
        }
    }

    private fun millisToCountdown(millis: Long): String {
        val diffTime = millis - System.currentTimeMillis()
        val diffDays = (diffTime / DAY_IN_MILLIS).toInt()
        val diffHours = (diffTime % DAY_IN_MILLIS / HOUR_IN_MILLIS).toInt()
        return diffDays.toString() + "d " + diffHours.toString() + 'h'

    }

    private fun dateToString(date: ZonedDateTime): String {
        val format = view.getContext()?.getString(R.string.format_date) + " " + view.getContext()?.getString(R.string.format_time)
        val zdt = date.withZoneSameInstant(ZoneId.systemDefault())
        return zdt.format(DateTimeFormatter.ofPattern(format))
    }
}