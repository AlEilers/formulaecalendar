package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String? = null,

        @SerializedName("Package")
        var `package`: String? = null,

        @SerializedName("Championships")
        var championships: Championships? = null,

        @SerializedName("Generated")
        var generated: String? = null
)
