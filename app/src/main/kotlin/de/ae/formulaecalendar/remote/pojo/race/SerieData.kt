package de.ae.formulaecalendar.remote.pojo.race

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

    @SerializedName("RaceName")
    @Expose
    var raceName: String? = null

    @SerializedName("RaceId")
    @Expose
    var raceId: String? = null

    @SerializedName("Package")
    @Expose
    var `package`: String? = null

    @SerializedName("Session")
    @Expose
    var session: Session? = null

    @SerializedName("Generated")
    @Expose
    var generated: String? = null

    override fun toString(): String {
        return "SerieData{" +
                "series='" + series + '\'' +
                ", championship='" + championship + '\'' +
                ", championshipId='" + championshipId + '\'' +
                ", raceName='" + raceName + '\'' +
                ", raceId='" + raceId + '\'' +
                ", _package='" + `package` + '\'' +
                ", session=" + session +
                ", generated='" + generated + '\'' +
                '}'
    }
}
