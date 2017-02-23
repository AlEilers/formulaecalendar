package de.ae.formulaecalendar.remote.rest

import de.ae.formulaecalendar.remote.pojo.calendar.Calendar
import de.ae.formulaecalendar.remote.pojo.driverstanding.DriverStanding
import de.ae.formulaecalendar.remote.pojo.race.Result
import de.ae.formulaecalendar.remote.pojo.series.Series
import de.ae.formulaecalendar.remote.pojo.teamstanding.TeamStanding
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by aeilers on 26.12.2016.
 */

interface RestService {

    @GET("/fe_server.php/")
    fun getSeries(): Observable<Series>

    @GET("/fe_server.php/")
    fun getCalendar(@Query("championship") championshipId: String): Observable<Calendar>

    @GET("/fe_server.php/?page=all")
    fun getResult(@Query("race") raceId: String): Observable<Result>

    @GET("/fe_server.php/?page=teams-standings")
    fun getTeamStanding(@Query("championship") championshipId: String): Observable<TeamStanding>

    @GET("/fe_server.php/?page=drivers-standings")
    fun getDriverStanding(@Query("championship") championshipId: String): Observable<DriverStanding>

    companion object {
        val BASE_URL = "http://forix-proxy-prod.formulae.corebine.com/"
    }
}
