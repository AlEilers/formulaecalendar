package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Calendar {

    @SerializedName("SerieData")
    @Expose
    var serieData: SerieData? = null

    override fun toString(): String {
        return "Calendar{" + "serieData=" + serieData + '}'
    }
}
