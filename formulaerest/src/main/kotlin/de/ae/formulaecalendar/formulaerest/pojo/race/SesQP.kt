package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class SesQP(

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

        @SerializedName("Laps")
        var laps: String?,

        @SerializedName("Time")
        var time: String?,

        @SerializedName("Delay")
        var delay: String?,

        @SerializedName("AvgSpeed")
        var avgSpeed: String?
)
