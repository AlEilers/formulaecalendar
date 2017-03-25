package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Series {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "Series{" + "serieData=" + serieData + '}'
    }
}
