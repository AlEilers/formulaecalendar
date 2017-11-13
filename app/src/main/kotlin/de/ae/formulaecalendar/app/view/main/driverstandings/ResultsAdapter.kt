package de.ae.formulaecalendar.app.view.main.driverstandings

import android.view.LayoutInflater
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.main.listfragment.ListAdapter
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData

/**
 * Created by aeilers on 17.02.2017.
 */
class ResultsAdapter : ListAdapter<ChampionshipData, ResultsHolder>() {
    private var data: ChampionshipData? = null

    override fun setContent(content: ChampionshipData) {
        this.data = content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.driver_results_item, parent, false)
        return ResultsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsHolder, position: Int) {
        val standing = data?.standings?.get(position)
        holder.pos.text = standing?.pos
        holder.name.text = standing?.driverName
        holder.description.text = standing?.teamName
        holder.points.text = standing?.totalPoints + " P."
    }

    override fun getItemCount() = data?.standings?.size ?: 0
}