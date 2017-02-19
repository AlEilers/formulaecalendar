package de.ae.formulaecalendar.view.main.calendar

import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData



/**
 * Created by aeilers on 17.02.2017.
 */
interface CalendarView {

    fun setContent(data: RaceCalendarData)

    fun setLoadingViewVisibility(visible: Boolean)

    fun setRecyclerViewVisibility(visible: Boolean)

    fun setSnackbarVisibility(visible: Boolean)
}