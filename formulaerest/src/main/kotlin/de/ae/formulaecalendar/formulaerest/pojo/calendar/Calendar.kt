package de.ae.formulaecalendar.formulaerest.pojo.calendar

import com.google.gson.annotations.SerializedName

data class Calendar(@SerializedName("SerieData") var serieData: SerieData? = null)
