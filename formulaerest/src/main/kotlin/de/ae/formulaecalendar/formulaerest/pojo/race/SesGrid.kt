package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

class SesGrid(

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

        @SerializedName("Time")
        var time: String? = null,

        @SerializedName("AvgSpeed")
        var avgSpeed: String? = null
)