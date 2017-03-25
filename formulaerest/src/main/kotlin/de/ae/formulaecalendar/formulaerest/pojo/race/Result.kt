package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "Race{" + "serieData=" + serieData + '}'
    }
}
