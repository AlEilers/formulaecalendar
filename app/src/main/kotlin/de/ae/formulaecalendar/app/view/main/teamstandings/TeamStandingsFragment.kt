package de.ae.formulaecalendar.app.view.main.teamstandings

import android.content.Context
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListFragment
import de.ae.formulaecalendar.app.view.main.listfragment.ListPresenter
import de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData


/**
 * Created by aeilers on 17.02.2017.
 */
class TeamStandingsFragment : ListFragment<ChampionshipData, ResultsHolder, ResultsAdapter>() {
    override val presenter: ListPresenter = TeamStandingsPresenter(this)

    override val layout: Int = R.layout.fragment_team_standings

    override val recyclerViewId: Int = R.id.team_result_list

    override val loadingViewId: Int = R.id.team_progress_bar

    override fun getRecyclerViewAdapter(context: Context): ResultsAdapter = ResultsAdapter()

    override var snackbarNotification: Int = R.string.no_data_fault

}