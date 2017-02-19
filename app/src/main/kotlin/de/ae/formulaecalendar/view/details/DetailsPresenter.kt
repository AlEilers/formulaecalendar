package de.ae.formulaecalendar.view.details

import android.content.Intent
import android.net.Uri
import android.util.Log
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.remote.DataStore
import de.ae.formulaecalendar.remote.RemoteStore
import de.ae.formulaecalendar.remote.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.remote.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.remote.pojo.race.Session
import de.ae.formulaecalendar.resource.LocalResourceStore
import de.ae.formulaecalendar.resource.ResourceStore
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by aeilers on 18.02.2017.
 */
class DetailsPresenter constructor(val view: DetailsView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler, val resource: ResourceStore) {

    var race: CalendarDatum? = null

    constructor(view: DetailsView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread(), LocalResourceStore)

    fun loadData(race: CalendarDatum?) {
        if (race != null) {
            view.setRaceLoadingVisibility(false)
            view.setMainViewVisibility(true)
            setContent(race)
        } else {
            view.setRaceLoadingVisibility(true)
            view.setMainViewVisibility(false)
            model.getCurrentRaceCalendar()
                    .subscribeOn(subscriber) // Create a new Thread
                    .observeOn(observer) // Use the UI thread
                    .subscribe(object : Subscriber<RaceCalendarData>() {
                        override fun onCompleted() {
                            view.setRaceLoadingVisibility(false)
                            view.setMainViewVisibility(true)
                            view.setRaceSnackbarVisibility(false)
                        }

                        override fun onError(t: Throwable) {
                            view.setRaceLoadingVisibility(false)
                            view.setMainViewVisibility(false)
                            view.setRaceSnackbarVisibility(true)
                            Log.w("DetailsPresenter", "Cannot load view: ${t.message}")
                        }

                        override fun onNext(data: RaceCalendarData) {
                            val nextRace = data.nextRace
                            if (nextRace != null) {
                                setContent(nextRace)
                            } else {
                                Log.w("DetailsPresenter", "Cannot load view: next race from Server is null")
                            }
                        }
                    })
        }
    }

    private fun setContent(race: CalendarDatum) {
        this.race = race
        val context = view.getContext()

        if (race == null) {
            view.setTitle(context.getString(R.string.no_event))
        } else {

            //set Header
            val title = race.raceName
            if (title != null) {
                view.setTitle(title)
            }

            val city = race.city
            if (city != null) {
                val resourceID = resource.getResourceId(city)
                if (resourceID != null) {
                    view.setImage(resourceID)
                }
            }

            //set Round
            view.setRound(context.getString(R.string.cal_event_round) + ' ' + race.sequence)

            //Set date
            val raceStartDate = race.raceStart.withZoneSameInstant(ZoneId.systemDefault())
            val date = raceStartDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.format_date)))
            view.setDate(date)

            //Set Quali Time
            val qualiStartDate = race.qualiStart.withZoneSameInstant(ZoneId.systemDefault())
            val qualiStart = qualiStartDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.format_time)))
            val qualiEndDate = race.qualiEnd.withZoneSameInstant(ZoneId.systemDefault())
            val qualiEnd = qualiEndDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.format_time)))
            view.setQualiTime(qualiStart + " - " + qualiEnd)

            //Set Race Time
            val raceStart = raceStartDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.format_time)))
            val raceEndDate = race.raceEnd.withZoneSameInstant(ZoneId.systemDefault())
            val raceEnd = raceEndDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.format_time)))
            view.setRaceTime(raceStart + " - " + raceEnd)

            //Set laps
            val laps_text = if (race.laps != null) race.laps else "0"
            view.setLaps(laps_text + " " + context.getString(R.string.details_laps))

            //set distance
            val length = Integer.parseInt(race.raceDistance) / 1000
            view.setDistance(length.toString() + " " + context.getString(R.string.details_distance))

            //set map
            val map = context.getString(R.string.details_on_map)
            view.setMap(String.format(map, race.city))

            if (race.hasRaceResults ?: false) {
                this.loadResults()
            } else {
                view.setNoResultsVisibility(true)
                view.setResultsLoadingVisibility(false)
                view.setResultsVisibility(false)
            }
        }
    }

    fun loadResults() {
        view.setNoResultsVisibility(false)
        view.setResultsLoadingVisibility(true)
        view.setResultsVisibility(false)

        model.getRaceResult(race?.raceId ?: "")
                .subscribeOn(subscriber)
                .observeOn(observer)
                .subscribe(object : Subscriber<Session>() {
                    override fun onCompleted() {
                        view.setResultsLoadingVisibility(false)
                        view.setResultsVisibility(true)
                        view.setResultsSnackbarVisibility(false)
                    }

                    override fun onError(t: Throwable) {
                        view.setResultsLoadingVisibility(false)
                        view.setResultsVisibility(false)
                        view.setResultsSnackbarVisibility(true)
                        Log.w("DetailsPresenter", "Cannot load view: ${t.message}")
                    }

                    override fun onNext(session: Session) {
                        val results = session.sesRace
                        if (results != null) {
                            view.setResults(results)
                        } else {
                            Log.w("DetailsPresenter", "Cannot load view: results from Server are null")
                        }
                    }
                })
    }

    fun openMap() {
        if (race != null) {
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + race?.city + ", " + race?.country)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.`package` = "com.google.android.apps.maps"
            view.getContext().startActivity(mapIntent)
        }
    }

}