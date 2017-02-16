package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DriverStanding {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "DriverStanding{" + "serieData=" + serieData + '}'
    }
}
