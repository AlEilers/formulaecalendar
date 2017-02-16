package de.ae.formulaecalendar.remote.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChampionshipsData {

    @SerializedName("ChampsData")
    @Expose
    var champsData: List<ChampsDatum>? = null

    override fun toString(): String {
        return "ChampionshipsData{" + "champsData=" + champsData + '}'
    }
}
