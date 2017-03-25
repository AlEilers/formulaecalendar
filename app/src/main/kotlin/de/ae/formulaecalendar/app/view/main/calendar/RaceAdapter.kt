package de.ae.formulaecalendar.app.view.main.calendar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.resource.LocalResourceStore
import de.ae.formulaecalendar.app.view.details.DetailsActivity
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter


/**
 * Created by aeilers on 17.02.2017.
 */
class RaceAdapter : RecyclerView.Adapter<RaceHolder> {
    private val context: Context
    private val format: String
    private val zone: ZoneId
    private var calendar: RaceCalendarData? = null
    private var nextRace: CalendarDatum? = null

    constructor(context: Context) {
        this.context = context
        this.format = context.getString(R.string.format_date) + " " + context.getString(R.string.format_time)
        this.zone = ZoneId.systemDefault()
    }

    fun setRaceCalendar(calendar: RaceCalendarData) {
        this.calendar = calendar
        this.nextRace = calendar.nextRace
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return RaceHolder(itemView)
    }

    override fun onBindViewHolder(holder: RaceHolder, position: Int) {
        val race = calendar?.calendarData?.get(position)

        holder.description.text = race?.raceName

        val zdt = race?.raceStart?.withZoneSameInstant(zone)
        holder.date.text = zdt?.format(DateTimeFormatter.ofPattern(format))

        val resourceView = LocalResourceStore.getResourceId(race?.city ?: "-")
        if (resourceView != null && resourceView >= 0) {
            holder.image.setImageResource(resourceView)
        }

        holder.image.setOnClickListener { startDetails(it, position) }

        if (race == nextRace) {
            holder.next.visibility = View.VISIBLE
        } else {
            holder.next.visibility = View.GONE
        }
    }

    private fun startDetails(view: View, pos: Int) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_RACE, calendar?.calendarData?.get(pos))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, view, DetailsActivity.ANIMATION)
        context.startActivity(intent, options.toBundle())
    }

    override fun getItemCount(): Int {
        return calendar?.calendarData?.size ?: 0
    }
}