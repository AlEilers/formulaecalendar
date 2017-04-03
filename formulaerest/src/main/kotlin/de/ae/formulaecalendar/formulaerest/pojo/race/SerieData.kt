package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String?,

        @SerializedName("Championship")
        var championship: String?,

        @SerializedName("ChampionshipId")
        var championshipId: String?,

        @SerializedName("RaceName")
        var raceName: String?,

        @SerializedName("RaceId")
        var raceId: String?,

        @SerializedName("Package")
        var `package`: String?,

        @SerializedName("Session")
        var session: Session?,

        @SerializedName("Generated")
        var generated: String?
)
