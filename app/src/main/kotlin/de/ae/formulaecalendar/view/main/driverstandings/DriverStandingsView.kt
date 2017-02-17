package de.ae.formulaecalendar.view.main.driverstandings

import de.ae.formulaecalendar.remote.pojo.driverstanding.ChampionshipData

/**
 * Created by alexa on 17.02.2017.
 */
interface DriverStandingsView {

    fun setContent(data: ChampionshipData)

    fun setLoadingViewVisibility(visible: Boolean)

    fun setRecyclerViewVisibility(visible: Boolean)

    fun setSnackbarVisibility(visible: Boolean)
}