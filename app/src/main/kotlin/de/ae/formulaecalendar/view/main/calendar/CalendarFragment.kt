package de.ae.formulaecalendar.view.main.calendar

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import kotlinx.android.synthetic.main.fragment_calendar.*
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_calendar.view.*


/**
 * Created by alexa on 17.02.2017.
 */
class CalendarFragment : Fragment(), CalendarView {
    private val presenter = CalendarPresenter(this)

    private var adapter: RaceAdapter? = null
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_calendar, container, false)
        cardList = view.calendar_cardList
        loadingView = view.calendar_progress_bar

        //set adapter for recycler view
        adapter = RaceAdapter(this.context)
        val llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        cardList?.setLayoutManager(llm)
        cardList?.setAdapter(adapter)

        //load content by presenter
        presenter.loadContent()

        return view
    }

    override fun setContent(data: RaceCalendarData) {
        adapter?.setRaceCalendar(data)
        adapter?.notifyDataSetChanged()
        calendar_cardList.scrollToPosition(data.posNextRace)
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
        if (visible) {
            snackbar = Snackbar.make(calendar_cardList, R.string.connection_fault, Snackbar.LENGTH_INDEFINITE)
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