package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

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
