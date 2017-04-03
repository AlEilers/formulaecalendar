package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.SerializedName

data class Standing(

        @SerializedName("Pos")
        var pos: String?,

        @SerializedName("TotalPoints")
        var totalPoints: String?,

        @SerializedName("TeamName")
        var teamName: String?,

        @SerializedName("TeamId")
        var teamId: String?,

        @SerializedName("Country")
        var country: String?,

        @SerializedName("Races")
        var races: Races?
)
