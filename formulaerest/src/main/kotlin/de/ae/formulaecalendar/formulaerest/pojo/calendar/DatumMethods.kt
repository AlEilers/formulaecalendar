package de.ae.formulaecalendar.formulaerest.pojo.calendar

import org.threeten.bp.ZoneId

/**
 * Created by alexa on 02.04.2017.
 */

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