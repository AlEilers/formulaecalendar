package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.SerializedName

data class SerieData(

        @SerializedName("Series")
        var series: String?,

        @SerializedName("Package")
        var `package`: String?,

        @SerializedName("Championships")
        var championships: Championships?,

        @SerializedName("Generated")
        var generated: String?
)
