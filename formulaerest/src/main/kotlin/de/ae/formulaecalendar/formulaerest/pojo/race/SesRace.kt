package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class SesRace(

        @SerializedName("Pos")
        var pos: String? = null,

        @SerializedName("Number")
        var number: String? = null,

        @SerializedName("TeamName")
        var teamName: String? = null,

        @SerializedName("TeamId")
        var teamId: String? = null,

        @SerializedName("DriverName")
        var driverName: String? = null,

        @SerializedName("DriverId")
        var driverId: String? = null,

        @SerializedName("FirstName")
        var firstName: String? = null,

        @SerializedName("LastName")
        var lastName: String? = null,

        @SerializedName("Country")
        var country: String? = null,

        @SerializedName("CarMake")
        var carMake: String? = null,

        @SerializedName("CarModel")
        var carModel: String? = null,

        @SerializedName("PolePosition")
        var polePosition: Boolean? = null,

        @SerializedName("FastestLap")
        var fastestLap: Boolean? = null,

        @SerializedName("DNF")
        var dnf: Boolean? = null,

        @SerializedName("Participation")
        var participation: String? = null,

        @SerializedName("Laps")
        var laps: String? = null,

        @SerializedName("Time")
        var time: String? = null,

        @SerializedName("AvgSpeed")
        var avgSpeed: String? = null,

        @SerializedName("BestTime")
        var bestTime: String? = null,

        @SerializedName("OnLap")
        var onLap: String? = null,

        @SerializedName("LastLapTime")
        var lastLapTime: String? = null,

        @SerializedName("Delay")
        var delay: String? = null,

        @SerializedName("Retirement")
        var retirement: String? = null
)
