package de.ae.formulaecalendar.remote.pojo.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaceCalendar {

    @SerializedName("RaceCalendarData")
    @Expose
    private RaceCalendarData raceCalendarData;

    public RaceCalendarData getRaceCalendarData() {
        return raceCalendarData;
    }

    public void setRaceCalendarData(RaceCalendarData raceCalendarData) {
        this.raceCalendarData = raceCalendarData;
    }

    @Override
    public String toString() {
        return "RaceCalendar{" +
                "raceCalendarData=" + raceCalendarData +
                '}';
    }
}
