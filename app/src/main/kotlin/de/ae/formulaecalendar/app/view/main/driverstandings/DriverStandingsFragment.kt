package de.ae.formulaecalendar.app.view.main.driverstandings

import android.content.Context
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListFragment
import de.ae.formulaecalendar.app.view.main.listfragment.ListPresenter
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData


/**
 * Created by aeilers on 17.02.2017.
 */
class DriverStandingsFragment : ListFragment<ChampionshipData, ResultsHolder, ResultsAdapter>() {
    override val presenter: ListPresenter = DriverStandingsPresenter(this)

    override val layout: Int = R.layout.fragment_driver_standings

    override val recyclerViewId: Int = R.id.driver_result_list

    override val loadingViewId: Int = R.id.driver_progress_bar

    override fun getRecyclerViewAdapter(context: Context): ResultsAdapter = ResultsAdapter()

    override var snackbarNotification: Int = R.string.no_data_fault
}