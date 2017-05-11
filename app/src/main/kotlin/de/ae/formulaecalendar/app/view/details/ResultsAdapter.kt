package de.ae.formulaecalendar.app.view.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.race.SesRace

/**
 * Created by aeilers on 18.02.2017.
 */
class ResultsAdapter : RecyclerView.Adapter<ResultsHolder>() {
    private val DNF = "DNF"
    private var results: List<SesRace>? = null

    fun setResults(results: List<SesRace>) {
        this.results = results
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.race_results_item, parent, false)
        return ResultsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsHolder, position: Int) {
        results?.get(position)?.let {
            if (it.dnf ?: false) {
                holder.pos.text = DNF
                holder.pos.textSize = 20f
            } else {
                holder.pos.text = it.pos
            }
            holder.name.text = it.driverName
            holder.description.text = it.teamName
        }
    }

    override fun getItemCount()= results?.size ?: 0
}