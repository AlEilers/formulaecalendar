package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChampionshipData {

    @SerializedName("Standings")
    @Expose
    var standings: List<Standing>? = null

    override fun toString(): String {
        return "ChampionshipData{" + "standings=" + standings + '}'
    }
}
