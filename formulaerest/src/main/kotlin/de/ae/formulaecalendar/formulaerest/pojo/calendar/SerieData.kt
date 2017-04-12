package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String? = null,

        @SerializedName("Championship")
        var championship: String? = null,

        @SerializedName("ChampionshipId")
        var championshipId: String? = null,

        @SerializedName("Package")
        var `package`: String? = null,

        @SerializedName("RaceCalendar")
        var raceCalendar: RaceCalendar? = null,

        @SerializedName("Generated")
        var generated: String? = null
)
