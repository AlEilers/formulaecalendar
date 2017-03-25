package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SesQP {

    @SerializedName("Pos")
    @Expose
    var pos: String? = null

    @SerializedName("Number")
    @Expose
    var number: String? = null

    @SerializedName("TeamName")
    @Expose
    var teamName: String? = null

    @SerializedName("TeamId")
    @Expose
    var teamId: String? = null

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

    @SerializedName("CarMake")
    @Expose
    var carMake: String? = null

    @SerializedName("CarModel")
    @Expose
    var carModel: String? = null

    @SerializedName("Laps")
    @Expose
    var laps: String? = null

    @SerializedName("Time")
    @Expose
    var time: String? = null

    @SerializedName("Delay")
    @Expose
    var delay: String? = null

    @SerializedName("AvgSpeed")
    @Expose
    var avgSpeed: String? = null

    override fun toString(): String {
        return "SesQP{" +
                "pos='" + pos + '\'' +
                ", number='" + number + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverId='" + driverId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                ", laps='" + laps + '\'' +
                ", time='" + time + '\'' +
                ", delay='" + delay + '\'' +
                ", avgSpeed='" + avgSpeed + '\'' +
                '}'
    }
}
