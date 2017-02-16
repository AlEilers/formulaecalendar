package de.ae.formulaecalendar.remote.pojo.series

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChampsDatum {

    @SerializedName("Championship")
    @Expose
    var championship: String? = null

    @SerializedName("ChampionshipId")
    @Expose
    var championshipId: String? = null

    @SerializedName("Status")
    @Expose
    var status: String? = null

    override fun toString(): String {
        return "ChampsDatum{" +
                "championship='" + championship + '\'' +
                ", championshipId='" + championshipId + '\'' +
                ", status='" + status + '\'' +
                '}'
    }
}
