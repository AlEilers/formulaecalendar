package de.ae.formulaecalendar.formulaerest.pojo.series

import com.google.gson.annotations.SerializedName

data class ChampionshipsData(@SerializedName("ChampsData") var champsData: List<ChampsDatum>?)