package de.ae.formulaecalendar.app.view.main.teamstandings

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData
import kotlinx.android.synthetic.main.fragment_team_standings.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class TeamStandingsFragment : Fragment(), TeamStandingsView {
    private var presenter: TeamStandingsPresenter? = null

    private var adapter: ResultsAdapter? = null
    private var snackbar: Snackbar? = null
    private var snackbarVisible = false

    private var cardList: RecyclerView? = null
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TeamStandingsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_standings, container, false)
        cardList = view.team_result_list
        loadingView = view.team_progress_bar

        //set adapter for recycler view
        adapter = ResultsAdapter()
        val llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        cardList?.layoutManager = llm
        cardList?.adapter = adapter

        //load content by presenter
        presenter?.loadContent()

        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        showSnackbar(snackbarVisible && isVisibleToUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        snackbar = null
        cardList = null
        loadingView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    override fun setContent(data: ChampionshipData) {
        adapter?.setResults(data)
        adapter?.notifyDataSetChanged()
    }

    override fun setLoadingViewVisibility(visible: Boolean) = when (visible) {
        true -> loadingView?.visibility = View.VISIBLE
        false -> loadingView?.visibility = View.INVISIBLE
    }

    override fun setRecyclerViewVisibility(visible: Boolean) = when (visible) {
        true -> cardList?.visibility = View.VISIBLE
        false -> cardList?.visibility = View.INVISIBLE
    }

    override fun setSnackbarVisibility(visible: Boolean) {
        snackbarVisible = visible
        showSnackbar(snackbarVisible && userVisibleHint)
    }

    private fun showSnackbar(visible: Boolean) = when (visible) {
        true -> cardList?.let {
            snackbar = Snackbar.make(it, R.string.connection_fault, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, { presenter?.loadContent() })
            snackbar?.show()
        }
        false -> snackbar?.dismiss()
    }
}