package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.SerializedName

data class Race(

        @SerializedName("RaceName")
        var raceName: String?,

        @SerializedName("RaceId")
        var raceId: String?,

        @SerializedName("RacePoints")
        var racePoints: String?,

        @SerializedName("PolePosition")
        var polePosition: Boolean?,

        @SerializedName("FastestLap")
        var fastestLap: Boolean?,

        @SerializedName("DNF")
        var dnf: Boolean?,

        @SerializedName("Round")
        var round: String?
)
