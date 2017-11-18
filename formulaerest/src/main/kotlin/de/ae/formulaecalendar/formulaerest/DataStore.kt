package de.ae.formulaecalendar.formulaerest

import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.race.Session
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampionshipsData
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampsDatum
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by aeilers on 12.01.2017.
 */

interface DataStore {

    fun getAllChampionShips(): Observable<ChampionshipsData?>

    fun getChampionShip(championshipId: String): Maybe<ChampsDatum?>

    fun getCurrentChampionShip(): Maybe<ChampsDatum?>

    fun getRaceCalendar(championshipId: String): Observable<RaceCalendarData?>

    fun getCurrentRaceCalendar(): Observable<RaceCalendarData?>

    fun getAllRacesCalendar(): Observable<RaceCalendarData?>

    fun getDriverStanding(championshipId: String): Observable<de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData?>

    fun getCurrentDriverStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData?>

    fun getTeamStanding(championshipId: String): Observable<de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData?>

    fun getCurrentTeamStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData?>

    fun getRaceResult(raceId: String): Observable<Session?>

}
