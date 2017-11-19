package de.ae.formulaecalendar.app.widget

import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.raceStart
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
class CountdownWidgetPresenter constructor(private val view: CountdownWidgetView,
                                           private val model: DataStore = RemoteStore,
                                           private val observer: Scheduler = AndroidSchedulers.mainThread(),
                                           private val subscriber: Scheduler = Schedulers.newThread())
    : Observer<RaceCalendarData?> {
    
    private val allRaces: MutableList<CalendarDatum> = mutableListOf()

    private val HOUR_IN_MILLIS = (1000 * 60 * 60).toLong()
    private val DAY_IN_MILLIS = (1000 * 60 * 60 * 24).toLong()

    fun loadWidget(name: String, previous: Long, date: String) {
        val currentTime = System.currentTimeMillis()
        if (previous > currentTime) {
            val countdown = millisToCountdown(previous)
            view.setContent(name, countdown, date, true)
        } else {
            model.getAllRacesCalendar()
                    .subscribeOn(subscriber)
                    .observeOn(observer)
                    .subscribe(this)
        }
    }

    override fun onSubscribe(d: Disposable?) {
        allRaces.clear()
    }

    override fun onComplete() {
        val next = allRaces.sortedBy { it.raceStart }
                .filter { it.raceStart.isAfter(ZonedDateTime.now()) }
                .firstOrNull()
        if (next != null) {
            val title = next.raceName ?: ""
            val countdown = millisToCountdown(next.raceStart.toEpochSecond() * 1000)
            val dateStr = dateToString(next.raceStart)
            view.setContent(title, countdown, dateStr, true)
            view.saveNext(title, next.raceStart.toEpochSecond() * 1000, dateStr)
        } else {
            val title = ""
            val countdown = view.getContext()?.getString(R.string.widget_no_next)
            val dateStr = ""
            view.setContent(title, countdown, dateStr, false)
            view.saveNext("", -1, "")
        }
    }

    override fun onError(t: Throwable) {
        val title = ""
        val countdown = view.getContext()?.getString(R.string.widget_loading)
        val dateStr = ""
        view.setContent(title, countdown, dateStr, false)
        Log.w("CountdownWidgetPresente", "Cannot load view: ${t.message}")
    }

    override fun onNext(raceCalendarData: RaceCalendarData?) {
        raceCalendarData?.calendarData?.let { allRaces.addAll(it) }
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