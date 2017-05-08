package de.ae.formulaecalendar.app.widget

import android.content.Context

/**
 * Created by aeilers on 18.02.2017.
 */
interface CountdownWidgetView {

    fun setContent(title: String?, countdown: String?, date: String?, openDetails: Boolean)

    fun saveNext(raceName: String, time: Long, date: String)

    fun getContext(): Context?
}