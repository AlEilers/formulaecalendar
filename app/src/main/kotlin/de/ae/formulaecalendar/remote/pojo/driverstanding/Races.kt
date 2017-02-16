package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Races {

    @SerializedName("Race")
    @Expose
    var race: List<Race>? = null

    override fun toString(): String {
        return "Races{" + "race=" + race + '}'
    }
}
