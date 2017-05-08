package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.SerializedName

data class ChampsDatum(

        @SerializedName("Championship")
        var championship: String? = null,

        @SerializedName("ChampionshipId")
        var championshipId: String? = null,

        @SerializedName("Status")
        var status: String? = null
)
