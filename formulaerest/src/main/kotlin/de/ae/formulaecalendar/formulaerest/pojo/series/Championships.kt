package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Championships {

    @SerializedName("ChampionshipsData")
    @Expose
    var championshipsData: ChampionshipsData? = null

    override fun toString(): String {
        return "Championships{" + "championshipsData=" + championshipsData + '}'
    }
}
