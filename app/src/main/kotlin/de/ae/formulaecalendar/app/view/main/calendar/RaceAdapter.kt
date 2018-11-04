package de.ae.formulaecalendar.app.view.main.calendar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.resource.LocalResourceStore
import de.ae.formulaecalendar.app.view.details.DetailsActivity
import de.ae.formulaecalendar.app.view.main.listfragment.ListAdapter
import de.ae.formulaecalendar.formulaerest.pojo.calendar.*
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter


/**
 * Created by aeilers on 17.02.2017.
 */
class RaceAdapter(val context: Context) : ListAdapter<RaceCalendarData, RaceHolder>() {
    private val format: String = context.getString(R.string.format_date) + " " + context.getString(R.string.format_time)
    private val zone: ZoneId = ZoneId.systemDefault()
    private var calendar: RaceCalendarData? = null
    private var nextRace: CalendarDatum? = null

    override fun setContent(content: RaceCalendarData) {
        this.calendar = content
        this.nextRace = content.nextRace()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return RaceHolder(itemView)
    }

    override fun onBindViewHolder(holder: RaceHolder, position: Int) {
        val race = calendar?.calendarData?.get(position)

        holder.description.text =
                if (race?.isRaceNameAvailable() == true) race?.raceName else race?.city

        val zdt = race?.raceStart?.withZoneSameInstant(zone)
        holder.date.text = zdt?.format(DateTimeFormatter.ofPattern(format))

        race?.circuitId?.let {
            val resourceId = LocalResourceStore.getResourceId(it)
            if (resourceId >= 0) {
                holder.image.setImageResource(resourceId)
            } else {
                holder.image.setImageDrawable(null)
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
