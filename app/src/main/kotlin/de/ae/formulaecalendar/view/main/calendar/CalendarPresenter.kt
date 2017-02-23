package de.ae.formulaecalendar.view.main.calendar

import android.util.Log
import de.ae.formulaecalendar.remote.DataStore
import de.ae.formulaecalendar.remote.RemoteStore
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by aeilers on 17.02.2017.
 */
class CalendarPresenter(val view: CalendarView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: CalendarView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadContent() {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        model.getCurrentRaceCalendar()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Observer<RaceCalendarData?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(true)
                        view.setSnackbarVisibility(false)
                    }

                    override fun onError(t: Throwable) {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(false)
                        view.setSnackbarVisibility(true)
                        Log.w("CalendarPresenter", "Cannot load view: ${t.message}")
                    }

                    override fun onNext(data: RaceCalendarData?) {
                        if (data != null) {
                            view.setContent(data)
                        }
                    }
                })
    }
}