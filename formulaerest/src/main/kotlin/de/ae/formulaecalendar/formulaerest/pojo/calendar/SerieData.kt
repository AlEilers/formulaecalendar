package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String?,

        @SerializedName("Championship")
        var championship: String?,

        @SerializedName("ChampionshipId")
        var championshipId: String?,

        @SerializedName("Package")
        var `package`: String?,

        @SerializedName("RaceCalendar")
        var raceCalendar: RaceCalendar?,

        @SerializedName("Generated")
        var generated: String?
)
