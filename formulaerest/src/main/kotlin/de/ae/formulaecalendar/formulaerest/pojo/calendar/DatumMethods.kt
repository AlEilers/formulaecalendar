package de.ae.formulaecalendar.formulaerest.pojo.calendar

import java.util.*

/**
 * Created by aeilers on 02.04.2017.
 */

val DATE_FORMAT = "dd. MMM yyyy"

fun RaceCalendarData.posNextRace(): Int {
    return calendarData?.let {getNextRace(it)} ?: -1
}

fun getNextRace(races: List<CalendarDatum>): Int {
    races.let {
        for (i in it.indices) {
            val raceDate = races.get(i).raceDate
            if (raceDate == null) {
                continue
            }
            if (isDateInFuture(raceDate)) {
                return i
            }
        }
    }
    return -1
}

fun isDateInFuture(date: Date) = date.after(Date())

fun RaceCalendarData.nextRace(): CalendarDatum? {
    val pos = this.posNextRace()
    return if (pos >= 0) calendarData?.get(pos) else null
}

fun CalendarDatum.isRaceNameAvailable() = !raceName.isNullOrBlank() && !raceName.equals("?")