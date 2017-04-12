package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName

class RaceCalendarData(@SerializedName("CalendarData") var calendarData: List<CalendarDatum>? = null)
