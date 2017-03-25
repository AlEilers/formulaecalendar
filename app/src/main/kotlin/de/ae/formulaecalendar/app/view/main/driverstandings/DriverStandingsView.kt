package de.ae.formulaecalendar.app.view.main.driverstandings

import de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData

/**
 * Created by aeilers on 17.02.2017.
 */
interface DriverStandingsView {

    fun setContent(data: ChampionshipData)

    fun setLoadingViewVisibility(visible: Boolean)

    fun setRecyclerViewVisibility(visible: Boolean)

    fun setSnackbarVisibility(visible: Boolean)
}