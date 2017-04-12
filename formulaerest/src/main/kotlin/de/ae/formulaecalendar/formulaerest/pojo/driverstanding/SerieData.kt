package de.ae.formulaecalendar.formulaerest.pojo.driverstanding

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

        @SerializedName("ChampionshipStanding")
        var championshipStanding: ChampionshipStanding? = null,

        @SerializedName("Generated")
        var generated: String? = null
)
