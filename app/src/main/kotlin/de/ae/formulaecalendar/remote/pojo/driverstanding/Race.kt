package de.ae.formulaecalendar.remote.pojo.driverstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Race {

    @SerializedName("RaceName")
    @Expose
    var raceName: String? = null

    @SerializedName("RaceId")
    @Expose
    var raceId: String? = null

    @SerializedName("RacePoints")
    @Expose
    var racePoints: String? = null

    @SerializedName("PolePosition")
    @Expose
    var polePosition: Boolean? = null

    @SerializedName("FastestLap")
    @Expose
    var fastestLap: Boolean? = null

    @SerializedName("DNF")
    @Expose
    var dnf: Boolean? = null

    @SerializedName("Round")
    @Expose
    var round: String? = null

    override fun toString(): String {
        return "Race{" +
                "raceName='" + raceName + '\'' +
                ", raceId='" + raceId + '\'' +
                ", racePoints='" + racePoints + '\'' +
                ", polePosition=" + polePosition +
                ", fastestLap=" + fastestLap +
                ", dNF=" + dnf +
                ", round='" + round + '\'' +
                '}'
    }
}
