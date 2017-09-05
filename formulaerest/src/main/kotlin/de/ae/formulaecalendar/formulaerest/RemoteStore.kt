package de.ae.formulaecalendar.formulaerest

import com.google.gson.GsonBuilder
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.race.Session
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampionshipsData
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampsDatum
import de.ae.formulaecalendar.formulaerest.rest.EncodingInterceptor
import de.ae.formulaecalendar.formulaerest.rest.RestService
import io.reactivex.Maybe
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by aeilers on 27.12.2016.
 */

object RemoteStore : DataStore {

    private val rest: RestService
    private var allChampionShips: Observable<ChampionshipsData?>
    private var currentChampionShip: Maybe<ChampsDatum?>
    private var currentRaceCalendar: Observable<RaceCalendarData?>
    private var driverStanding: Observable<de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData?>
    private var teamStanding: Observable<de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData?>

    init {
        val gson = GsonBuilder()
                .setDateFormat("d.M.yyyy")
                .create()

        val client = OkHttpClient.Builder()
                //.addInterceptor(EncodingInterceptor()) // reenable if another encoding than utf8 is used
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(RestService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        rest = retrofit.create(RestService::class.java)

        allChampionShips = createAllChampionShips()
        currentChampionShip = createCurrentChampionShip()
        currentRaceCalendar = createCurrentRaceCalendar()
        driverStanding = createDriverStanding()
        teamStanding = createTeamStanding()
    }

    private fun createAllChampionShips(): Observable<ChampionshipsData?> {
        return rest.getSeries()
                .map { it.serieData?.championships?.championshipsData }
                .doOnError { allChampionShips = createAllChampionShips() }
                .cache()
    }

    override fun getAllChampionShips(): Observable<ChampionshipsData?> {
        return allChampionShips
    }

    private fun createCurrentChampionShip(): Maybe<ChampsDatum?> {
        return allChampionShips
                .map { it?.champsData }
                .flatMapIterable { it }
                .sorted { datum1, datum2 -> datum1.championshipId?.compareTo(datum2.championshipId ?: "") ?: Int.MIN_VALUE }
                .filter { it.status == "Active" || it.status == "Past" }
                .lastElement()
                .doOnError { currentChampionShip = createCurrentChampionShip() }
                .cache()
    }

    override fun getCurrentChampionShip(): Maybe<ChampsDatum?> {
        return currentChampionShip
    }

    private fun createCurrentRaceCalendar(): Observable<RaceCalendarData?> {
        return currentChampionShip
                .flatMapObservable { rest.getCalendar(it?.championshipId ?: "") }
                .map { it.serieData?.raceCalendar?.raceCalendarData }
                .doOnError { currentRaceCalendar = createCurrentRaceCalendar() }
                .cache()
    }

    override fun getCurrentRaceCalendar(): Observable<RaceCalendarData?> {
        return currentRaceCalendar
    }

    private fun createDriverStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData?> {
        return currentChampionShip
                .flatMapObservable { rest.getDriverStanding(it?.championshipId ?: "") }
                .map { it.serieData?.championshipStanding?.championshipData }
                .doOnError { driverStanding = createDriverStanding() }
                .cache()
    }

    override fun getDriverStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.driverstanding.ChampionshipData?> {
        return driverStanding
    }

    private fun createTeamStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData?> {
        return currentChampionShip
                .flatMapObservable { rest.getTeamStanding(it?.championshipId ?: "") }
                .map { it.serieData?.championshipStanding?.championshipData }
                .doOnError { teamStanding = createTeamStanding() }
                .cache()
    }

    override fun getTeamStanding(): Observable<de.ae.formulaecalendar.formulaerest.pojo.teamstanding.ChampionshipData?> {
        return teamStanding
    }

    override fun getRaceResult(raceId: String): Observable<Session?> {
        return rest.getResult(raceId)
                .map { it.serieData?.session }
    }
}
