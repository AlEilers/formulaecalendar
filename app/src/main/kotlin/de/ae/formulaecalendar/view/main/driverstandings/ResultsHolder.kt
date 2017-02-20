package de.ae.formulaecalendar.view.main.driverstandings

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.driver_results_item.view.*

/**
 * Created by aeilers on 19.02.2017.
 */
class ResultsHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pos = itemView.driver_pos
    val name = itemView.driver_name
    val description = itemView.driver_description
    val points = itemView.driver_points
}