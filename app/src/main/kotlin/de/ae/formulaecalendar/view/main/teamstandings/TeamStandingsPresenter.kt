package de.ae.formulaecalendar.view.main.teamstandings

import android.util.Log
import de.ae.formulaecalendar.remote.DataStore
import de.ae.formulaecalendar.remote.RemoteStore
import de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by alexa on 17.02.2017.
 */
class TeamStandingsPresenter(val view: TeamStandingsView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: TeamStandingsView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadContent() {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        model.getTeamStanding()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Subscriber<ChampionshipData>() {
                    override fun onCompleted() {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(true)
                        view.setSnackbarVisibility(false)
                    }

                    override fun onError(t: Throwable) {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(false)
                        view.setSnackbarVisibility(true)
                        Log.w("TeamStandingsPresenter","Cannot load view: ${t.message}")
                    }

                    override fun onNext(championshipData: ChampionshipData) {
                        view.setContent(championshipData)
                    }
                })
    }
}