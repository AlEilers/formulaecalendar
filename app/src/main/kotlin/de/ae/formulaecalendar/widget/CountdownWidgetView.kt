package de.ae.formulaecalendar.widget

import android.content.Context

/**
 * Created by alexa on 18.02.2017.
 */
interface CountdownWidgetView {

    fun setContent(title: String?, countdown: String?, date: String?, openDetails: Boolean)

    fun saveNext(millis: Long)

    fun getContext(): Context?
}