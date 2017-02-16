package de.ae.formulaecalendar.remote

import com.google.gson.GsonBuilder
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.remote.pojo.race.Session
import de.ae.formulaecalendar.remote.pojo.series.ChampionshipsData
import de.ae.formulaecalendar.remote.pojo.series.ChampsDatum
import de.ae.formulaecalendar.remote.rest.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable


/**
 * Created by aeilers on 27.12.2016.
 */

object RemoteStore : DataStore {

    private val rest: RestService
    private var allChampionShips: Observable<ChampionshipsData>
    private var currentChampionShip: Observable<ChampsDatum>
    private var currentRaceCalendar: Observable<RaceCalendarData>
    private var driverStanding: Observable<de.ae.formulaecalendar.remote.pojo.driverstanding.ChampionshipData>
    private var teamStanding: Observable<de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData>

    init {
        val gson = GsonBuilder()
                .setDateFormat("d.M.yyyy")
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(RestService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        rest = retrofit.create(RestService::class.java)

        allChampionShips = createAllChampionShips()
        currentChampionShip = createCurrentChampionShip()
        currentRaceCalendar = createCurrentRaceCalendar()
        driverStanding = createDriverStanding()
        teamStanding = createTeamStanding()
    }

    private fun createAllChampionShips(): Observable<ChampionshipsData> {
        return rest.getSeries()
                .map { it.serieData!!.championships!!.championshipsData!! }
                .doOnError { allChampionShips = createAllChampionShips() }
                .cache()
    }

    override fun getAllChampionShips(): Observable<ChampionshipsData> {
        return allChampionShips
    }

    private fun createCurrentChampionShip(): Observable<ChampsDatum> {
        return allChampionShips
                .map { it.champsData }
                .flatMapIterable { it }
                .first { it.status.equals("Active") }
                .doOnError { currentChampionShip = createCurrentChampionShip() }
                .cache()
    }

    override fun getCurrentChampionShip(): Observable<ChampsDatum> {
        return currentChampionShip
    }

    private fun createCurrentRaceCalendar(): Observable<RaceCalendarData> {
        return currentChampionShip
                .flatMap { rest.getCalendar(it.championshipId!!) }
                .map { it.serieData!!.raceCalendar.raceCalendarData }
                .doOnError { currentRaceCalendar = createCurrentRaceCalendar() }
                .cache()
    }

    override fun getCurrentRaceCalendar(): Observable<RaceCalendarData> {
        return currentRaceCalendar
    }

    private fun createDriverStanding(): Observable<de.ae.formulaecalendar.remote.pojo.driverstanding.ChampionshipData>{
        return currentChampionShip
                .flatMap { rest.getDriverStanding(it.championshipId!!)}
                .map { it.serieData!!.championshipStanding!!.championshipData!! }
                .doOnError { driverStanding = createDriverStanding() }
                .cache()
    }

    override fun getDriverStanding(): Observable<de.ae.formulaecalendar.remote.pojo.driverstanding.ChampionshipData> {
        return driverStanding
    }

    private fun createTeamStanding(): Observable<de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData>{
        return currentChampionShip
                .flatMap { rest.getTeamStanding(it.championshipId!!) }
                .map { it.serieData!!.championshipStanding!!.championshipData!! }
                .doOnError { teamStanding = createTeamStanding() }
                .cache()
    }

    override fun getTeamStanding(): Observable<de.ae.formulaecalendar.remote.pojo.teamstanding.ChampionshipData> {
        return teamStanding
    }

    override fun getRaceResult(raceId: String): Observable<Session> {
        return rest.getResult(raceId)
                .map { it.serieData!!.session!! }
    }
}
