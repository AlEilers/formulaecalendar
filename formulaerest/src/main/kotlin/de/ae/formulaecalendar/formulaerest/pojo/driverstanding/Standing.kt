package de.ae.formulaecalendar.formulaerest.pojo.driverstanding

import com.google.gson.annotations.SerializedName

data class Standing(

        @SerializedName("Pos")
        var pos: String? = null,

        @SerializedName("TotalPoints")
        var totalPoints: String? = null,

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

        @SerializedName("Number")
        var number: String? = null,

        @SerializedName("TeamName")
        var teamName: String? = null,

        @SerializedName("TeamId")
        var teamId: String? = null,

        @SerializedName("Races")
        var races: Races? = null
)
