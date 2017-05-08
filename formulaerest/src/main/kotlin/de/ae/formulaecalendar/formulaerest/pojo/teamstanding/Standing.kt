package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.SerializedName

data class Standing(

        @SerializedName("Pos")
        var pos: String? = null,

        @SerializedName("TotalPoints")
        var totalPoints: String? = null,

        @SerializedName("TeamName")
        var teamName: String? = null,

        @SerializedName("TeamId")
        var teamId: String? = null,

        @SerializedName("Country")
        var country: String? = null,

        @SerializedName("Races")
        var races: Races? = null
)
