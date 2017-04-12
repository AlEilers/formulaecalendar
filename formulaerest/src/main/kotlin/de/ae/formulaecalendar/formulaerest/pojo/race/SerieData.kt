package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String? = null,

        @SerializedName("Championship")
        var championship: String? = null,

        @SerializedName("ChampionshipId")
        var championshipId: String? = null,

        @SerializedName("RaceName")
        var raceName: String? = null,

        @SerializedName("RaceId")
        var raceId: String? = null,

        @SerializedName("Package")
        var `package`: String? = null,

        @SerializedName("Session")
        var session: Session? = null,

        @SerializedName("Generated")
        var generated: String? = null
)
