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
import de.ae.formulaecalendar.formulaerest.pojo.calendar.nextRace
import de.ae.formulaecalendar.formulaerest.pojo.calendar.raceStart
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter


/**
 * Created by aeilers on 17.02.2017.
 */
class RaceAdapter(val context: Context) : RecyclerView.Adapter<RaceHolder>() {
    private val format: String = context.getString(R.string.format_date) + " " + context.getString(R.string.format_time)
    private val zone: ZoneId = ZoneId.systemDefault()
    private var calendar: RaceCalendarData? = null
    private var nextRace: CalendarDatum? = null

    fun setRaceCalendar(calendar: RaceCalendarData) {
        this.calendar = calendar
        this.nextRace = calendar.nextRace()
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

        race?.city?.let {
            LocalResourceStore.getResourceId(it)?.let {
                if (it >= 0) holder.image.setImageResource(it)
            }
        }

        holder.image.setOnClickListener { startDetails(it, position) }

        if (race == nextRace) holder.next.visibility = View.VISIBLE
        else holder.next.visibility = View.GONE
    }

    private fun startDetails(view: View, pos: Int) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_RACE, calendar?.calendarData?.get(pos))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, view, DetailsActivity.ANIMATION)
        context.startActivity(intent, options.toBundle())
    }

    override fun getItemCount() = calendar?.calendarData?.size ?: 0
}
