package de.ae.formulaecalendar.formulaerest.pojo.calendar

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

    @SerializedName("RaceCalendar")
    @Expose
    var raceCalendar: RaceCalendar? = null

    @SerializedName("Generated")
    @Expose
    var generated: String? = null

    override fun toString(): String {
        return "SerieData{" +
                "series='" + series + '\'' +
                ", championship='" + championship + '\'' +
                ", championshipId='" + championshipId + '\'' +
                ", _package='" + `package` + '\'' +
                ", raceCalendar=" + raceCalendar +
                ", generated='" + generated + '\'' +
                '}'
    }
}
