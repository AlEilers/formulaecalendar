package de.ae.formulaecalendar.app.view.main.calendar

import android.content.Context
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListFragment
import de.ae.formulaecalendar.app.view.main.listfragment.ListPresenter
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.posNextRace


/**
 * Created by aeilers on 17.02.2017.
 */
class CalendarFragment : ListFragment<RaceCalendarData, RaceHolder, RaceAdapter>() {
    override val presenter: ListPresenter = CalendarPresenter(this)

    override val layout: Int = R.layout.fragment_calendar

    override val recyclerViewId: Int = R.id.calendar_cardList

    override val loadingViewId: Int = R.id.calendar_progress_bar

    override fun getRecyclerViewAdapter(context: Context): RaceAdapter = RaceAdapter(context)

    override var snackbarNotification: Int = R.string.no_data_fault

    override fun setContent(content: RaceCalendarData) {
        super.setContent(content)
        contentList?.scrollToPosition(content.posNextRace())
    }
}