package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.SerializedName

data class ChampionshipStanding(

        @SerializedName("ChampionshipType")
        var championshipType: String? = null,

        @SerializedName("ChampionshipData")
        var championshipData: ChampionshipData? = null
)
