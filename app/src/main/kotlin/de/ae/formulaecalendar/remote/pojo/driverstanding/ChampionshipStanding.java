
package de.ae.formulaecalendar.remote.pojo.driverstanding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChampionshipStanding {

    @SerializedName("ChampionshipType")
    @Expose
    private String championshipType;
    @SerializedName("ChampionshipData")
    @Expose
    private ChampionshipData championshipData;

    public String getChampionshipType() {
        return championshipType;
    }

    public void setChampionshipType(String championshipType) {
        this.championshipType = championshipType;
    }

    public ChampionshipData getChampionshipData() {
        return championshipData;
    }

    public void setChampionshipData(ChampionshipData championshipData) {
        this.championshipData = championshipData;
    }

    @Override
    public String toString() {
        return "ChampionshipStanding{" +
                "championshipType='" + championshipType + '\'' +
                ", championshipData=" + championshipData +
                '}';
    }
}
