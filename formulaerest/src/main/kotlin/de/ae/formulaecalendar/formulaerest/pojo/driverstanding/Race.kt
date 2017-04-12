package de.ae.formulaecalendar.formulaerest.pojo.driverstanding

import com.google.gson.annotations.SerializedName

data class Race(

        @SerializedName("RaceName")
        var raceName: String? = null,

        @SerializedName("RaceId")
        var raceId: String? = null,

        @SerializedName("RacePoints")
        var racePoints: String? = null,

        @SerializedName("PolePosition")
        var polePosition: Boolean? = null,

        @SerializedName("FastestLap")
        var fastestLap: Boolean? = null,

        @SerializedName("DNF")
        var dnf: Boolean? = null,

        @SerializedName("Round")
        var round: String? = null
)
