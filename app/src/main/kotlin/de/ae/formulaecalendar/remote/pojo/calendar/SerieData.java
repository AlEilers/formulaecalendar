package de.ae.formulaecalendar.remote.pojo.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SerieData {

    @SerializedName("Series")
    @Expose
    private String series;
    @SerializedName("Championship")
    @Expose
    private String championship;
    @SerializedName("ChampionshipId")
    @Expose
    private String championshipId;
    @SerializedName("Package")
    @Expose
    private String _package;
    @SerializedName("RaceCalendar")
    @Expose
    private RaceCalendar raceCalendar;
    @SerializedName("Generated")
    @Expose
    private String generated;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getChampionship() {
        return championship;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public String getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(String championshipId) {
        this.championshipId = championshipId;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public RaceCalendar getRaceCalendar() {
        return raceCalendar;
    }

    public void setRaceCalendar(RaceCalendar raceCalendar) {
        this.raceCalendar = raceCalendar;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    @Override
    public String toString() {
        return "SerieData{" +
                "series='" + series + '\'' +
                ", championship='" + championship + '\'' +
                ", championshipId='" + championshipId + '\'' +
                ", _package='" + _package + '\'' +
                ", raceCalendar=" + raceCalendar +
                ", generated='" + generated + '\'' +
                '}';
    }
}
