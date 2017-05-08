package de.ae.formulaecalendar.formulaerest.pojo.teamstanding

import com.google.gson.annotations.SerializedName

data class ChampionshipData(@SerializedName("Standings") var standings: List<Standing>? = null)