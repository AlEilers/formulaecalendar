package de.ae.formulaecalendar.view.details

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.race_results_item.view.*


/**
 * Created by aeilers on 17.02.2017.
 */
class ResultsHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var pos = itemView.results_pos
    val name = itemView.results_name
    val description = itemView.results_description
}