package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChampionshipStanding {

    @SerializedName("ChampionshipType")
    @Expose
    var championshipType: String? = null
    @SerializedName("ChampionshipData")
    @Expose
    var championshipData: ChampionshipData? = null

    override fun toString(): String {
        return "ChampionshipStanding{" +
                "championshipType='" + championshipType + '\'' +
                ", championshipData=" + championshipData +
                '}'
    }
}
