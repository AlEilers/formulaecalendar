package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZoneId
import java.io.Serializable
import java.util.*

class CalendarDatum : Serializable {

    @SerializedName("Sequence")
    @Expose
    var sequence: String? = null

    @SerializedName("RaceDate")
    @Expose
    var raceDate: Date? = null

    @SerializedName("RaceName")
    @Expose
    var raceName: String? = null

    @SerializedName("RaceId")
    @Expose
    var raceId: String? = null

    @SerializedName("City")
    @Expose
    var city: String? = null

    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("hasRaceResults")
    @Expose
    var hasRaceResults: Boolean? = null

    @SerializedName("hasSessionResults")
    @Expose
    var hasSessionResults: Boolean? = null

    @SerializedName("CircuitName")
    @Expose
    var circuitName: String? = null

    @SerializedName("CircuitId")
    @Expose
    var circuitId: String? = null

    @SerializedName("CircuitPerimeter")
    @Expose
    var circuitPerimeter: String? = null

    @SerializedName("Laps")
    @Expose
    var laps: String? = null

    @SerializedName("ScheduledLaps")
    @Expose
    var scheduledLaps: String? = null

    @SerializedName("RaceDistance")
    @Expose
    var raceDistance: String? = null

    var zoneId: ZoneId? = null

    override fun toString(): String {
        return "CalendarDatum{" +
                "sequence='" + sequence + '\'' +
                ", raceDate='" + raceDate + '\'' +
                ", raceName='" + raceName + '\'' +
                ", raceId='" + raceId + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", hasRaceResults=" + hasRaceResults +
                ", hasSessionResults=" + hasSessionResults +
                ", circuitName='" + circuitName + '\'' +
                ", circuitId='" + circuitId + '\'' +
                ", circuitPerimeter='" + circuitPerimeter + '\'' +
                ", laps='" + laps + '\'' +
                ", scheduledLaps='" + scheduledLaps + '\'' +
                ", raceDistance='" + raceDistance + '\'' +
                '}'
    }
}
