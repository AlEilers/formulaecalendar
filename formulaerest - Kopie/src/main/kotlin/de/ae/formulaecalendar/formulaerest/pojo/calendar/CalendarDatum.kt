package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.Calendar

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

    private var zoneId: ZoneId? = null

    //TODO doesn't find all ZoneIds eg: Marrakesh
    private fun getZoneId(): ZoneId? {
        if (zoneId != null) {
            return zoneId
        } else {
            for (zone in ZoneId.getAvailableZoneIds()) {
                val cityString = city?.toLowerCase()
                if (cityString != null) {
                    if (zone.toLowerCase().contains(cityString.replace("\\s+".toRegex(), "")) || zone.toLowerCase().contains(cityString.replace("\\s+".toRegex(), "_"))) {
                        zoneId = ZoneId.of(zone)
                        return zoneId
                    }
                }
            }
            return null
        }
    }

    val raceStart: ZonedDateTime
        get() {
            val cal = java.util.Calendar.getInstance()
            cal.time = raceDate
            val date = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))
            val time = LocalTime.of(RACE_START_HOUR, 0)
            val id = this.getZoneId()
            if (id != null) {
                return ZonedDateTime.of(date, time, id)
            } else {
                return ZonedDateTime.of(date, time, ZoneId.systemDefault())
            }
        }

    val raceEnd: ZonedDateTime
        get() = this.raceStart.plusMinutes(RACE_DURATION_MINUTES)

    val qualiStart: ZonedDateTime
        get() {
            val cal = java.util.Calendar.getInstance()
            cal.time = raceDate
            val date = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))
            val time = LocalTime.of(QUALI_START_HOUR, 0)
            val id = this.getZoneId()
            if (id != null) {
                return ZonedDateTime.of(date, time, id)
            } else {
                return ZonedDateTime.of(date, time, ZoneId.systemDefault())
            }
        }

    val qualiEnd: ZonedDateTime
        get() = this.qualiStart.plusMinutes(QUALI_DURATION_MINUTES)

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

    companion object {
        private const val serialVersionUID = 1L
        private const val RACE_START_HOUR = 16
        private const val RACE_DURATION_MINUTES = 60L
        private const val QUALI_START_HOUR = 12
        private const val QUALI_DURATION_MINUTES = 60L
    }
}
