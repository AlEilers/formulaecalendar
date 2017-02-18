package de.ae.formulaecalendar.view.main.calendar

import android.util.Log
import de.ae.formulaecalendar.remote.DataStore
import de.ae.formulaecalendar.remote.RemoteStore
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by alexa on 17.02.2017.
 */
class CalendarPresenter(val view: CalendarView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: CalendarView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadContent() {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        model.getCurrentRaceCalendar()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Subscriber<RaceCalendarData>() {
                    override fun onCompleted() {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(true)
                        view.setSnackbarVisibility(false)
                    }

                    override fun onError(t: Throwable) {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(false)
                        view.setSnackbarVisibility(true)
                        Log.w("CalendarPresenter","Cannot load view: ${t.message}")
                    }

                    override fun onNext(data: RaceCalendarData) {
                        view.setContent(data)
                    }
                })
    }
}