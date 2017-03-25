package de.ae.formulaecalendar.view.main.driverstandings

import android.util.Log
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by aeilers on 17.02.2017.
 */
class DriverStandingsPresenter(val view: DriverStandingsView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: DriverStandingsView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadContent() {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        model.getDriverStanding()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Observer<ChampionshipData?> {
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
                        Log.w("DriverStandingPresenter", "Cannot load view: ${t.message}")
                    }

                    override fun onNext(championshipData: ChampionshipData?) {
                        if (championshipData != null) {
                            view.setContent(championshipData)
                        }
                    }
                })
    }
}