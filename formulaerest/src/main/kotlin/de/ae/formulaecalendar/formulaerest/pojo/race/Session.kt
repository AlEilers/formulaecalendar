package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Session {

    @SerializedName("Ses_FP1")
    @Expose
    var sesFP1: List<SesFP1>? = null

    @SerializedName("Ses_FP2")
    @Expose
    var sesFP2: List<SesFP2>? = null

    @SerializedName("Ses_QP")
    @Expose
    var sesQP: List<SesQP>? = null

    @SerializedName("Ses_race")
    @Expose
    var sesRace: List<SesRace>? = null

    @SerializedName("Ses_grid")
    @Expose
    var sesGrid: List<SesGrid>? = null

    @SerializedName("Ses_fl")
    @Expose
    var sesFl: List<SesFl>? = null

    @SerializedName("Ses_null")
    @Expose
    var sesNull: String? = null

    override fun toString(): String {
        return "Session{" +
                "sesFP1=" + sesFP1 +
                ", sesFP2=" + sesFP2 +
                ", sesQP=" + sesQP +
                ", sesRace=" + sesRace +
                ", sesGrid=" + sesGrid +
                ", sesFl=" + sesFl +
                ", sesNull='" + sesNull + '\'' +
                '}'
    }
}
