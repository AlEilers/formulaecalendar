package de.ae.formulaecalendar.view.main.calendar

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.calendar_item.view.*

/**
 * Created by aeilers on 17.02.2017.
 */
class RaceHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    val description = itemView.card_race_description
    val date = itemView.card_race_date
    val image = itemView.card_race_image
    val next = itemView.card_race_next
}