package de.ae.formulaecalendar.view.main.teamstandings

import de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData

/**
 * Created by alexa on 17.02.2017.
 */
interface TeamStandingsView {

    fun setContent(data: ChampionshipData)

    fun setLoadingViewVisibility(visible: Boolean)

    fun setRecyclerViewVisibility(visible: Boolean)

    fun setSnackbarVisibility(visible: Boolean)
}