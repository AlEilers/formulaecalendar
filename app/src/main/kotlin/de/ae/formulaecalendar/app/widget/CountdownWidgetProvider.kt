package de.ae.formulaecalendar.app.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.RemoteViews
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.details.DetailsActivity

/**
 * Created by aeilers on 18.02.2017.
 */
abstract class CountdownWidgetProvider constructor(val layout: Int) : AppWidgetProvider(), CountdownWidgetView {
    private val PREF_TIME = "widget_next_race_time2"    // '2' because needed new pref key to avoid bugs
    private val PREF_NAME = "widget_next_race_name2"
    private val PREF_DATE = "widget_next_race_date2"

    private val presenter = CountdownWidgetPresenter(this)

    private var context: Context? = null
    private var appWidgetManager: AppWidgetManager? = null
    private var appWidgetIds: IntArray? = null
    private var prefs: SharedPreferences? = null

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        this.context = context
        this.appWidgetManager = appWidgetManager
        this.appWidgetIds = appWidgetIds

        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val name = prefs?.getString(PREF_NAME, "") ?: ""
        val time = prefs?.getLong(PREF_TIME, 0) ?: 0
        val date = prefs?.getString(PREF_DATE, "") ?: ""

        presenter.loadWidget(name, time, date)
    }

    override fun setContent(title: String?, countdown: String?, date: String?, openDetails: Boolean) {
        appWidgetIds?.let {
            for (widgetId in it) {

                val remoteViews = RemoteViews(context?.packageName, layout)

                // Set the text
                title?.let { remoteViews.setTextViewText(R.id.widget_text_city, it) }
                countdown?.let { remoteViews.setTextViewText(R.id.widget_text_time, it) }
                date?.let { remoteViews.setTextViewText(R.id.widget_text_date, it) }

                //set on click
                if (openDetails) {
                    val intent = Intent(context, DetailsActivity::class.java)
                    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                    remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent)
                }

                appWidgetManager?.updateAppWidget(widgetId, remoteViews)
            }
        }
    }


    override fun saveNext(raceName: String, time: Long, date: String) {
        prefs?.edit()?.let {
            it.putString(PREF_NAME, raceName)
            it.putLong(PREF_TIME, time)
            it.putString(PREF_DATE, date)
            it.apply()
        }
    }

    override fun getContext(): Context? {
        return context
    }
}

class BrightCountdownWidgetProvider : CountdownWidgetProvider(R.layout.widget_countdown_bright)

class DarkCountdownWidgetProvider : CountdownWidgetProvider(R.layout.widget_countdown_dark)
