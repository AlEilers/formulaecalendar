package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class Session(

        @SerializedName("Ses_FP1")
        var sesFP1: List<SesFP1>?,

        @SerializedName("Ses_FP2")
        var sesFP2: List<SesFP2>?,

        @SerializedName("Ses_QP")
        var sesQP: List<SesQP>?,

        @SerializedName("Ses_race")
        var sesRace: List<SesRace>?,

        @SerializedName("Ses_grid")
        var sesGrid: List<SesGrid>?,

        @SerializedName("Ses_fl")
        var sesFl: List<SesFl>?,

        @SerializedName("Ses_null")
        var sesNull: String?
)
