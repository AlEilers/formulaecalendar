package de.ae.formulaecalendar.formulaerest.pojo.driverstanding

import com.google.gson.annotations.SerializedName

data class ChampionshipStanding(

        @SerializedName("ChampionshipType")
        var championshipType: String?,

        @SerializedName("ChampionshipData")
        var championshipData: ChampionshipData?
)
