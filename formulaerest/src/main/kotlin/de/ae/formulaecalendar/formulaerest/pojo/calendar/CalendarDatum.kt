package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZoneId
import java.io.Serializable
import java.util.*

data class CalendarDatum(
        @SerializedName("Sequence")
        var sequence: String? = null,

        @SerializedName("RaceDate")
        var raceDate: Date? = null,

        @SerializedName("RaceName")
        var raceName: String? = null,

        @SerializedName("RaceId")
        var raceId: String? = null,

        @SerializedName("City")
        var city: String? = null,

        @SerializedName("Country")
        var country: String? = null,

        @SerializedName("hasRaceResults")
        var hasRaceResults: Boolean? = null,

        @SerializedName("hasSessionResults")
        var hasSessionResults: Boolean? = null,

        @SerializedName("CircuitName")
        var circuitName: String? = null,

        @SerializedName("CircuitId")
        var circuitId: String? = null,

        @SerializedName("CircuitPerimeter")
        var circuitPerimeter: String? = null,

        @SerializedName("Laps")
        var laps: String? = null,

        @SerializedName("ScheduledLaps")
        var scheduledLaps: String? = null,

        @SerializedName("RaceDistance")
        var raceDistance: String? = null
) : Serializable {

    var zoneId: ZoneId? = null
}
