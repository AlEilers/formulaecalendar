package de.ae.formulaecalendar.remote.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import de.ae.formulaecalendar.remote.pojo.series.SerieData

class Series {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "Series{" + "serieData=" + serieData + '}'
    }
}
