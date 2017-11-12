package de.ae.formulaecalendar.app.view.main.listfragment

import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData

/**
 * Created by aeilers on 17.02.2017.
 */
interface ListView<T> {

    fun setContent(content: T)

    fun setLoadingViewVisibility(visible: Boolean)

    fun setRecyclerViewVisibility(visible: Boolean)

    fun showSnackbar(notification: Int = R.string.connection_fault)

    fun hideSnackbar()
}