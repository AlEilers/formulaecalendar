package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SerieData {

    @SerializedName("Series")
    @Expose
    var series: String? = null

    @SerializedName("Championship")
    @Expose
    var championship: String? = null

    @SerializedName("ChampionshipId")
    @Expose
    var championshipId: String? = null

    @SerializedName("Package")
    @Expose
    var `package`: String? = null

    @SerializedName("ChampionshipStanding")
    @Expose
    var championshipStanding: ChampionshipStanding? = null

    @SerializedName("Generated")
    @Expose
    var generated: String? = null

    override fun toString(): String {
        return "SerieData{" +
                "series='" + series + '\'' +
                ", championship='" + championship + '\'' +
                ", championshipId='" + championshipId + '\'' +
                ", _package='" + `package` + '\'' +
                ", championshipStanding=" + championshipStanding +
                ", generated='" + generated + '\'' +
                '}'
    }
}
