package de.ae.formulaecalendar.formulaerest.pojo.race

import com.google.gson.annotations.SerializedName

data class Session(

        @SerializedName("Ses_FP1")
        var sesFP1: List<SesFP1>? = null,

        @SerializedName("Ses_FP2")
        var sesFP2: List<SesFP2>? = null,

        @SerializedName("Ses_QP")
        var sesQP: List<SesQP>? = null,

        @SerializedName("Ses_race")
        var sesRace: List<SesRace>? = null,

        @SerializedName("Ses_grid")
        var sesGrid: List<SesGrid>? = null,

        @SerializedName("Ses_fl")
        var sesFl: List<SesFl>? = null,

        @SerializedName("Ses_null")
        var sesNull: String? = null
)
