package de.ae.formulaecalendar.remote.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import de.ae.formulaecalendar.remote.pojo.series.Championships

class SerieData {

    @SerializedName("Series")
    @Expose
    var series: String? = null

    @SerializedName("Package")
    @Expose
    var `package`: String? = null

    @SerializedName("Championships")
    @Expose
    var championships: Championships? = null

    @SerializedName("Generated")
    @Expose
    var generated: String? = null

    override fun toString(): String {
        return "SerieData{" +
                "series='" + series + '\'' +
                ", _package='" + `package` + '\'' +
                ", championships=" + championships +
                ", generated='" + generated + '\'' +
                '}'
    }
}
