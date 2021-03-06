package de.ae.formulaecalendar.app.view.main.calendar

import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListPresenter
import de.ae.formulaecalendar.app.view.main.listfragment.ListView
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
class CalendarPresenter(private val view: ListView<RaceCalendarData>,
                        private val model: DataStore = RemoteStore,
                        private val observer: Scheduler = AndroidSchedulers.mainThread(),
                        private val subscriber: Scheduler = Schedulers.newThread())
    : ListPresenter, Observer<RaceCalendarData?> {

    /**
     * if season is null or empty get current season,
     * otherwise get selected season
     */
    override fun loadContent(season: String?) {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        val data = if (season == null || season.isEmpty()) {
            model.getCurrentRaceCalendar()
        } else {
            model.getRaceCalendar(season)
        }

        data.subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(this)
    }

    override fun onComplete() {
        view.setLoadingViewVisibility(false)
        view.setRecyclerViewVisibility(true)
        view.hideSnackbar()
    }

    override fun onNext(value: RaceCalendarData?) {
        value?.let { view.setContent(it) }
    }

    override fun onSubscribe(d: Disposable?) {
    }

    override fun onError(e: Throwable) {
        view.setLoadingViewVisibility(false)
        view.setRecyclerViewVisibility(false)
        if (e is NullPointerException) {
            view.showSnackbar(R.string.no_data_fault)
        } else {
            view.showSnackbar(R.string.connection_fault)
        }
        Log.w("CalendarPresenter", "Cannot load view: ${e.message}")
    }
}