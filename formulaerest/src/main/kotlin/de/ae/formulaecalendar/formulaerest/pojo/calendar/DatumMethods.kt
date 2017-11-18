package de.ae.formulaecalendar.formulaerest.pojo.calendar

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.util.Calendar

/**
 * Created by aeilers on 02.04.2017.
 */

// RaceCalendarData Extension

fun RaceCalendarData.posNextRace(): Int {
    calendarData?.let {
        for (i in it.indices) {
            if (calendarData!![i].raceEnd.toEpochSecond() * 1000 > System.currentTimeMillis()) {
                return i
            }
        }
    }
    return -1
}

fun RaceCalendarData.nextRace(): CalendarDatum? {
    val pos = this.posNextRace()
    return if (pos >= 0) calendarData?.get(pos) else null
}

// CalendarDatum Extensions

private const val RACE_START_HOUR = 16
private const val RACE_DURATION_MINUTES = 60L
private const val QUALI_START_HOUR = 12
private const val QUALI_DURATION_MINUTES = 60L

//TODO doesn't find all ZoneIds eg: Marrakesh
fun CalendarDatum.getZoneId(): ZoneId? {
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

val CalendarDatum.raceStart: ZonedDateTime
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

val CalendarDatum.raceEnd: ZonedDateTime
    get() = this.raceStart.plusMinutes(RACE_DURATION_MINUTES)

val CalendarDatum.qualiStart: ZonedDateTime
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

val CalendarDatum.qualiEnd: ZonedDateTime
    get() = this.qualiStart.plusMinutes(QUALI_DURATION_MINUTES)