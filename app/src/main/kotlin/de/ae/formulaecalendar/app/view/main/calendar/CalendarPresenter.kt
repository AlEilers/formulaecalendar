package de.ae.formulaecalendar.app.view.main.calendar

import android.util.Log
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by aeilers on 17.02.2017.
 */
class CalendarPresenter(val view: CalendarView,
                        val model: DataStore = RemoteStore,
                        val observer: Scheduler = AndroidSchedulers.mainThread(),
                        val subscriber: Scheduler = Schedulers.newThread()) {

    fun loadContent(season: String? = null) {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        val data = if (season == null || season.isEmpty()) {
            model.getCurrentRaceCalendar()
        } else {
            model.getRaceCalendar(season)
        }

        data.subscribeOn(subscriber) // Create a new Thread
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
                        data?.let { view.setContent(it) }
                    }
                })
    }
}