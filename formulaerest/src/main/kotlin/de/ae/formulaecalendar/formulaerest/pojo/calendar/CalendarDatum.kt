package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZoneId
import java.io.Serializable
import java.util.*

data class CalendarDatum(
        @SerializedName("Sequence")
        var sequence: String?,

        @SerializedName("RaceDate")
        var raceDate: Date?,

        @SerializedName("RaceName")
        var raceName: String?,

        @SerializedName("RaceId")
        var raceId: String?,

        @SerializedName("City")
        var city: String?,

        @SerializedName("Country")
        var country: String?,

        @SerializedName("hasRaceResults")
        var hasRaceResults: Boolean?,

        @SerializedName("hasSessionResults")
        var hasSessionResults: Boolean?,

        @SerializedName("CircuitName")
        var circuitName: String?,

        @SerializedName("CircuitId")
        var circuitId: String?,

        @SerializedName("CircuitPerimeter")
        var circuitPerimeter: String?,

        @SerializedName("Laps")
        var laps: String?,

        @SerializedName("ScheduledLaps")
        var scheduledLaps: String?,

        @SerializedName("RaceDistance")
        var raceDistance: String?
) : Serializable {

    var zoneId: ZoneId? = null
}
