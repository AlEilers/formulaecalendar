package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RaceCalendar {

    @SerializedName("RaceCalendarData")
    @Expose
    var raceCalendarData: RaceCalendarData? = null

    override fun toString(): String {
        return "RaceCalendar{" +
                "raceCalendarData=" + raceCalendarData +
                '}'
    }
}
