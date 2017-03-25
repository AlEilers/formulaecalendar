package de.ae.formulaecalendar.view.details

import android.content.Context
import de.ae.formulaecalendar.formulaerest.pojo.race.SesRace

/**
 * Created by aeilers on 18.02.2017.
 */
interface DetailsView {

    fun getContext(): Context

    //race
    fun setImage(resourceId: Int)

    fun setTitle(title: String)

    fun setRaceLoadingVisibility(visible: Boolean)

    fun setMainViewVisibility(visible: Boolean)

    fun setRaceSnackbarVisibility(visible: Boolean)

    //round
    fun setRound(round: String)

    fun setDate(date: String)

    fun setQualiTime(time: String)

    fun setRaceTime(time: String)

    //circiut
    fun setDistance(distance: String)

    fun setLaps(laps: String)

    fun setMap(map: String)

    //results
    fun setNoResultsVisibility(visible: Boolean)

    fun setResultsLoadingVisibility(visible: Boolean)

    fun setResultsVisibility(visible: Boolean)

    fun setResultsSnackbarVisibility(visible: Boolean)

    fun setResults(results: List<SesRace>)
}