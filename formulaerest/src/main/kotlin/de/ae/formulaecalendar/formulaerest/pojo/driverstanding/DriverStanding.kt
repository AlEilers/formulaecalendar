package de.ae.formulaecalendar.formulaerest.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.SerieData

class DriverStanding {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "DriverStanding{" + "serieData=" + serieData + '}'
    }
}
