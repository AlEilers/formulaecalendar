package de.ae.formulaecalendar.view.main.teamstandings

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData
import de.ae.formulaecalendar.view.details.ResultsHolder


/**
 * Created by alexa on 17.02.2017.
 */
class ResultsAdapter: RecyclerView.Adapter<ResultsHolder>(){
    private var data: ChampionshipData? = null

    fun setResults(data: ChampionshipData) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.results_item, parent, false)
        return ResultsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsHolder, position: Int) {
        val standing = data?.standings?.get(position)
        holder.pos.text = standing?.pos
        holder.name.text = standing?.teamName
        holder.description.text = standing?.country
    }

    override fun getItemCount(): Int {
        return data?.standings?.size ?: 0
    }
}