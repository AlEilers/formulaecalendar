package de.ae.formulaecalendar.view.main.calendar

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.race_card.view.*

/**
 * Created by alexa on 17.02.2017.
 */
class RaceHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    val description: TextView = itemView.card_race_description
    val date: TextView = itemView.card_race_date
    val image: ImageView = itemView.card_race_image
    val next: TextView = itemView.card_race_next
}