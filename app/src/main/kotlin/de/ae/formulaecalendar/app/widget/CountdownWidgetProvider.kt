package de.ae.formulaecalendar.app.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.RemoteViews
import com.jakewharton.threetenabp.AndroidThreeTen
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.details.DetailsActivity

/**
 * Created by aeilers on 18.02.2017.
 */
abstract class CountdownWidgetProvider constructor(val layout: Int) : AppWidgetProvider(), CountdownWidgetView {
    private val PREF = "widget_next_race"

    private val presenter = CountdownWidgetPresenter(this)

    private var context: Context? = null
    private var appWidgetManager: AppWidgetManager? = null
    private var appWidgetIds: IntArray? = null
    private var prefs: SharedPreferences? = null

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        this.context = context
        this.appWidgetManager = appWidgetManager
        this.appWidgetIds = appWidgetIds

        //initialize ThreeTen
        AndroidThreeTen.init(context)

        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val time = prefs?.getLong(PREF, 0)

        presenter.loadWidget(time)
    }

    override fun setContent(title: String?, countdown: String?, date: String?, openDetails: Boolean) {
        val ids = appWidgetIds
        if (ids != null) {
            for (widgetId in ids) {

                val remoteViews = RemoteViews(context?.packageName, layout)

                // Set the text
                if (title != null) {
                    remoteViews.setTextViewText(R.id.widget_text_city, title)
                }
                if (countdown != null) {
                    remoteViews.setTextViewText(R.id.widget_text_time, countdown)
                }
                if (date != null) {
                    remoteViews.setTextViewText(R.id.widget_text_date, date)
                }

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


    override fun saveNext(millis: Long) {
        prefs?.edit()?.putLong(PREF, millis)?.apply()
    }

    override fun getContext(): Context? {
        return context
    }
}
