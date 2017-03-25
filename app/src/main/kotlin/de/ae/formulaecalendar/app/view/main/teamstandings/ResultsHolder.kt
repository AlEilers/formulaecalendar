package de.ae.formulaecalendar.app.view.main.teamstandings

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.team_results_item.view.*

/**
 * Created by aeilers on 19.02.2017.
 */
class ResultsHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pos = itemView.team_pos
    val name = itemView.team_name
    val points = itemView.team_points
}