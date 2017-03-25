package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamStanding {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "TeamStanding{" + "serieData=" + serieData + '}'
    }
}
