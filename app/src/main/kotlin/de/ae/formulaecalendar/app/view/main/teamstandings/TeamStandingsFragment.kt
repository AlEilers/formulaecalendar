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
import kotlinx.android.synthetic.main.fragment_team_standings.*
import kotlinx.android.synthetic.main.fragment_team_standings.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class TeamStandingsFragment : Fragment(), TeamStandingsView {
    private val presenter: TeamStandingsPresenter = TeamStandingsPresenter(this)

    private var adapter: ResultsAdapter? = null
    private var snackbar: Snackbar? = null
    private var fragmentVisible = false

    private var cardList: RecyclerView? = null
    private var loadingView: View? = null

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)

        this.fragmentVisible = visible

        //show snackbar if fragment is visible again and snackbar is available ( != null )
        if (visible) {
            snackbar?.show()
        } else {
            snackbar?.dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_standings, container, false)
        cardList = view.team_result_list
        loadingView = view.team_progress_bar

        //set adapter for recycler view
        adapter = ResultsAdapter()
        val llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        cardList?.setLayoutManager(llm)
        cardList?.setAdapter(adapter)

        //load content by presenter
        presenter?.loadContent()

        return view
    }

    override fun setContent(data: ChampionshipData) {
        adapter?.setResults(data)
        adapter?.notifyDataSetChanged()
    }

    override fun setLoadingViewVisibility(visible: Boolean) {
        if (visible) {
            loadingView?.setVisibility(View.VISIBLE)
        } else {
            loadingView?.setVisibility(View.INVISIBLE)
        }
    }

    override fun setRecyclerViewVisibility(visible: Boolean) {
        if (visible) {
            cardList?.setVisibility(View.VISIBLE)
        } else {
            cardList?.setVisibility(View.INVISIBLE)
        }
    }

    override fun setSnackbarVisibility(visible: Boolean) {
        val view = cardList
        if (visible && view!=null) {
            snackbar = Snackbar.make(team_result_list, R.string.connection_fault, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, { presenter.loadContent() })
            if (fragmentVisible) {
                snackbar?.show()
            }
        } else {
            snackbar?.dismiss()
            snackbar = null
        }
    }
}