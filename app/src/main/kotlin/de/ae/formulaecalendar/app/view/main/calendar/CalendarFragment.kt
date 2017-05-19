package de.ae.formulaecalendar.app.view.main.calendar

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.posNextRace
import kotlinx.android.synthetic.main.fragment_calendar.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class CalendarFragment : Fragment(), CalendarView {
    private var presenter: CalendarPresenter? = null

    private var adapter: RaceAdapter? = null
    private var snackbar: Snackbar? = null
    private var snackbarVisible = false

    private var cardList: RecyclerView? = null
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CalendarPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        cardList = view.calendar_cardList
        loadingView = view.calendar_progress_bar

        //set adapter for recycler view
        adapter = RaceAdapter(this.context)
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

    override fun setContent(data: RaceCalendarData) {
        adapter?.setRaceCalendar(data)
        adapter?.notifyDataSetChanged()
        cardList?.scrollToPosition(data.posNextRace())
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