package de.ae.formulaecalendar.app.view.main.driverstandings

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.observer.Observable
import de.ae.formulaecalendar.app.view.observer.Observer
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData
import kotlinx.android.synthetic.main.fragment_driver_standings.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class DriverStandingsFragment : Fragment(), DriverStandingsView, Observer<String?> {
    private var presenter: DriverStandingsPresenter? = null
    private var selectedSeason: Observable<String?>? = null

    private var adapter: ResultsAdapter? = null
    private var snackbar: Snackbar? = null
    private var snackbarVisible = false
    private var snackbarNotification = R.string.connection_fault
    private var fragmentIsVisible = false

    private var cardList: RecyclerView? = null
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DriverStandingsPresenter(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        selectedSeason = context as? Observable<String?>
        selectedSeason?.register(this)
    }

    override fun onDetach() {
        super.onDetach()
        selectedSeason?.unregister(this)
        selectedSeason = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_driver_standings, container, false)
        cardList = view.driver_result_list
        loadingView = view.driver_progress_bar

        //set adapter for recycler view
        adapter = ResultsAdapter()
        val llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        cardList?.layoutManager = llm
        cardList?.adapter = adapter

        //load content by presenter
        presenter?.loadContent(selectedSeason?.getCurrentValue())

        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        fragmentIsVisible = isVisibleToUser
        if (snackbarVisible && fragmentIsVisible) {
            this.createSnackbar()
        } else {
            this.dismissSnackbar()
        }
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

    override fun showSnackbar(notification: Int) {
        snackbarVisible = true
        snackbarNotification = notification
        if (snackbarVisible && fragmentIsVisible) {
            createSnackbar()
        }
    }

    private fun createSnackbar() {
        cardList?.let {
            snackbar = Snackbar.make(it, snackbarNotification, Snackbar.LENGTH_INDEFINITE)
            snackbar?.setAction(R.string.snackbar_retry, { presenter?.loadContent(selectedSeason?.getCurrentValue()) })
            snackbar?.show()
        }
    }

    override fun hideSnackbar() {
        snackbarVisible = false
        this.dismissSnackbar()
    }

    private fun dismissSnackbar() {
        snackbar?.dismiss()
    }

    override fun update(newValue: String?) {
        presenter?.loadContent(newValue)
    }
}