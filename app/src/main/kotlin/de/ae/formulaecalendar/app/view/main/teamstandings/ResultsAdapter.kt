package de.ae.formulaecalendar.app.view.main.teamstandings

import android.view.LayoutInflater
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListAdapter
import de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData


/**
 * Created by aeilers on 17.02.2017.
 */
class ResultsAdapter : ListAdapter<ChampionshipData, ResultsHolder>() {
    private var data: ChampionshipData? = null

    override fun setContent(content: ChampionshipData) {
        this.data = content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.team_results_item, parent, false)
        return ResultsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsHolder, position: Int) {
        val standing = data?.standings?.get(position)
        holder.pos.text = standing?.pos
        holder.name.text = standing?.teamName
        holder.points.text = standing?.totalPoints + " P."
    }

    override fun getItemCount(): Int {
        return data?.standings?.size ?: 0
    }
}