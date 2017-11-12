package de.ae.formulaecalendar.app.view.main.teamstandings

import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by aeilers on 17.02.2017.
 */
class TeamStandingsPresenter(val view: TeamStandingsView,
                             val model: DataStore = RemoteStore,
                             val observer: Scheduler = AndroidSchedulers.mainThread(),
                             val subscriber: Scheduler = Schedulers.newThread()) {

    fun loadContent(season: String? = null) {
        view.setLoadingViewVisibility(true)
        view.setRecyclerViewVisibility(false)

        val data = if (season == null || season.isEmpty()) {
            model.getCurrentTeamStanding()
        } else {
            model.getTeamStanding(season)
        }

        data.subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : Observer<ChampionshipData?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(true)
                        view.hideSnackbar()
                    }

                    override fun onError(t: Throwable) {
                        view.setLoadingViewVisibility(false)
                        view.setRecyclerViewVisibility(false)
                        if (t is NullPointerException) {
                            view.showSnackbar(R.string.no_data_fault)
                        } else {
                            view.showSnackbar(R.string.connection_fault)
                        }
                        Log.w("TeamStandingsPresenter", "Cannot load view: ${t.message}")
                    }

                    override fun onNext(championshipData: ChampionshipData?) {
                        championshipData?.let { view.setContent(it) }
                    }
                })
    }
}