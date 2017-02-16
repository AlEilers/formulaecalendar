package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Standing {

    @SerializedName("Pos")
    @Expose
    var pos: String? = null

    @SerializedName("TotalPoints")
    @Expose
    var totalPoints: String? = null

    @SerializedName("DriverName")
    @Expose
    var driverName: String? = null

    @SerializedName("DriverId")
    @Expose
    var driverId: String? = null

    @SerializedName("FirstName")
    @Expose
    var firstName: String? = null

    @SerializedName("LastName")
    @Expose
    var lastName: String? = null

    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("Number")
    @Expose
    var number: String? = null

    @SerializedName("TeamName")
    @Expose
    var teamName: String? = null

    @SerializedName("TeamId")
    @Expose
    var teamId: String? = null

    @SerializedName("Races")
    @Expose
    var races: Races? = null

    override fun toString(): String {
        return "Standing{" +
                "pos='" + pos + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverId='" + driverId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", number='" + number + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", races=" + races +
                '}'
    }
}
