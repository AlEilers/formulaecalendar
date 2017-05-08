package de.ae.formulaecalendar.formulaerest.remote.pojo.driverstanding

import com.google.gson.annotations.SerializedName
import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.SerieData

data class DriverStanding(@SerializedName("SerieData") var serieData: SerieData? = null)
