package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RaceCalendarData {

    @SerializedName("CalendarData")
    @Expose
    var calendarData: List<CalendarDatum>? = null

    override fun toString(): String {
        return "RaceCalendarData{" + "calendarData=" + calendarData + '}'
    }
}
