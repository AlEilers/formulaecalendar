package de.ae.formulaecalendar.remote.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RaceCalendarData {

    @SerializedName("CalendarData")
    @Expose
    var calendarData: List<CalendarDatum>? = null

    override fun toString(): String {
        return "RaceCalendarData{" + "calendarData=" + calendarData + '}'
    }

    val posNextRace: Int
        get() {
            for (i in calendarData!!.indices) {
                if (calendarData!![i].raceEnd.toEpochSecond() * 1000 > System.currentTimeMillis()) {
                    return i
                }
            }
            return -1
        }

    val nextRace: CalendarDatum?
        get() {
            val pos = this.posNextRace
            return if (pos >= 0) calendarData!![pos] else null
        }
}
