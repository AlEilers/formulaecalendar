package de.ae.formulaecalendar.app.view.main.calendar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListFragment
import de.ae.formulaecalendar.app.view.main.listfragment.ListPresenter
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.posNextRace
import kotlinx.android.synthetic.main.fragment_calendar.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class CalendarFragment : ListFragment<RaceCalendarData, RaceHolder, RaceAdapter>() {

    override val layout: Int = R.layout.fragment_calendar

    override fun getRecyclerView(view: View): RecyclerView = view.calendar_cardList

    override fun getLoadingView(view: View): View = view.calendar_progress_bar

    override fun getRecyclerViewAdapter(context: Context): RaceAdapter = RaceAdapter(context)

    override fun createPresenter(): ListPresenter = CalendarPresenter(this)

    override var snackbarNotification: Int = R.string.no_data_fault

    override fun setContent(content: RaceCalendarData) {
        super.setContent(content)
        contentList?.scrollToPosition(content.posNextRace())
    }
}