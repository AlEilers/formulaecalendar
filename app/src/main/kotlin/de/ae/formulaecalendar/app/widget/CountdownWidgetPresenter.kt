package de.ae.formulaecalendar.app.widget

import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.*
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by aeilers on 18.02.2017.
 */
class CountdownWidgetPresenter constructor(private val view: CountdownWidgetView,
                                           private val model: DataStore = RemoteStore,
                                           private val observer: Scheduler = AndroidSchedulers.mainThread(),
                                           private val subscriber: Scheduler = Schedulers.newThread())
    : Observer<RaceCalendarData?> {

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
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
        val nextRace = allRaces.sortedBy { it.raceDate }
                .filter { race -> race.raceDate?.let { isDateInFuture(it) } ?: false }
                .firstOrNull()
        if (nextRace != null) {
            val title =
                    if (nextRace.isRaceNameAvailable()) {
                        nextRace.raceName ?: ""
                    } else {
                        nextRace.city ?: ""
                    }
            val millisecondsToNextRace = nextRace.raceDate?.time ?: 0
            val countdownString = millisToCountdown(millisecondsToNextRace)
            val dateStr = simpleDateFormat.format(nextRace.raceDate)
            view.setContent(title, countdownString, dateStr, true)
            view.saveNext(title, millisecondsToNextRace, dateStr)
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
}