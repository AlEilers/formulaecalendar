package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class SesRace(

        @SerializedName("Pos")
        var pos: String?,

        @SerializedName("Number")
        var number: String?,

        @SerializedName("TeamName")
        var teamName: String?,

        @SerializedName("TeamId")
        var teamId: String?,

        @SerializedName("DriverName")
        var driverName: String?,

        @SerializedName("DriverId")
        var driverId: String?,

        @SerializedName("FirstName")
        var firstName: String?,

        @SerializedName("LastName")
        var lastName: String?,

        @SerializedName("Country")
        var country: String?,

        @SerializedName("CarMake")
        var carMake: String?,

        @SerializedName("CarModel")
        var carModel: String?,

        @SerializedName("PolePosition")
        var polePosition: Boolean?,

        @SerializedName("FastestLap")
        var fastestLap: Boolean?,

        @SerializedName("DNF")
        var dnf: Boolean?,

        @SerializedName("Participation")
        var participation: String?,

        @SerializedName("Laps")
        var laps: String?,

        @SerializedName("Time")
        var time: String?,

        @SerializedName("AvgSpeed")
        var avgSpeed: String?,

        @SerializedName("BestTime")
        var bestTime: String?,

        @SerializedName("OnLap")
        var onLap: String?,

        @SerializedName("LastLapTime")
        var lastLapTime: String?,

        @SerializedName("Delay")
        var delay: String?,

        @SerializedName("Retirement")
        var retirement: String?
)
