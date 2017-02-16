package de.ae.formulaecalendar.remote.pojo.teamstanding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Standing {

    @SerializedName("Pos")
    @Expose
    var pos: String? = null

    @SerializedName("TotalPoints")
    @Expose
    var totalPoints: String? = null

    @SerializedName("TeamName")
    @Expose
    var teamName: String? = null

    @SerializedName("TeamId")
    @Expose
    var teamId: String? = null

    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("Races")
    @Expose
    var races: Races? = null

    override fun toString(): String {
        return "Standing{" +
                "pos='" + pos + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                ", country='" + country + '\'' +
                ", races=" + races +
                '}'
    }
}
