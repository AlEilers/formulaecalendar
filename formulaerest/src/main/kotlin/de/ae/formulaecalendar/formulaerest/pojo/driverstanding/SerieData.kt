package de.ae.formulaecalendar.formulaerest.pojo.driverstanding

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

        @SerializedName("ChampionshipStanding")
        var championshipStanding: ChampionshipStanding?,

        @SerializedName("Generated")
        var generated: String?
)
