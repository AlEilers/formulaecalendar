package de.ae.formulaecalendar.remote

import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.remote.pojo.race.Session
import de.ae.formulaecalendar.remote.pojo.series.ChampionshipsData
import de.ae.formulaecalendar.remote.pojo.series.ChampsDatum
import rx.Observable

/**
 * Created by aeilers on 12.01.2017.
 */

interface DataStore {

    fun getAllChampionShips(): Observable<ChampionshipsData?>

    fun getCurrentChampionShip(): Observable<ChampsDatum?>

    fun getCurrentRaceCalendar(): Observable<RaceCalendarData?>

    fun getDriverStanding(): Observable<de.ae.formulaecalendar.remote.pojo.driverstanding.ChampionshipData?>

    fun getTeamStanding(): Observable<de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData?>

    fun getRaceResult(raceId: String): Observable<Session?>

}
