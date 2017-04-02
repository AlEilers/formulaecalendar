package de.ae.formulaecalendar.app.view.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.resource.LocalResourceStore
import de.ae.formulaecalendar.app.resource.ResourceStore
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.nextRace
import de.ae.formulaecalendar.formulaerest.pojo.race.Session
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by aeilers on 18.02.2017.
 */
class DetailsPresenter {
    var mFirebaseAnalytics: FirebaseAnalytics? = null
    private val showRaceEvent = "show_race"
    private val showRaceIDParam = FirebaseAnalytics.Param.ITEM_ID
    private val showRaceNameParam = FirebaseAnalytics.Param.ITEM_NAME
    private val showraceBundle = Bundle()

    private val view: DetailsView
    private val model: DataStore
    private val observer: Scheduler
    private val subscriber: Scheduler
    private val resource: ResourceStore

    private var race: CalendarDatum? = null

    constructor(view: DetailsView, model: DataStore, observer: Scheduler, subscriber: Scheduler, resource: ResourceStore) {
        this.view = view
        this.model = model
        this.observer = observer
        this.subscriber = subscriber
        this.resource = resource
        try {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext())
        } catch (e: NullPointerException) {
            Log.w("DetailsPresenter", "Cannot instanciate FirebaseAnalytics, probably in test mode")
        }
    }

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
                    .subscribe(object : Observer<RaceCalendarData?> {
                        override fun onSubscribe(d: Disposable?) {

                        }

                        override fun onComplete() {
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

                        override fun onNext(data: RaceCalendarData?) {
                            val nextRace = data?.nextRace()
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
        logToFirebase(race)

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

    private fun logToFirebase(race: CalendarDatum) {
        if (mFirebaseAnalytics != null) {
            showraceBundle.putInt(showRaceIDParam, race.raceId?.toInt() ?: -1)
            showraceBundle.putString(showRaceNameParam, race.raceName)
            mFirebaseAnalytics?.logEvent(showRaceEvent, showraceBundle)
        }
    }

    fun loadResults() {
        view.setNoResultsVisibility(false)
        view.setResultsLoadingVisibility(true)
        view.setResultsVisibility(false)

        model.getRaceResult(race?.raceId ?: "")
                .subscribeOn(subscriber)
                .observeOn(observer)
                .subscribe(object : Observer<Session?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {
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

                    override fun onNext(session: Session?) {
                        val results = session?.sesRace
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
            try {
                val gmmIntentUri = Uri.parse("geo:0,0?q=" + race?.city + ", " + race?.country)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.`package` = "com.google.android.apps.maps"
                view.getContext().startActivity(mapIntent)
            }catch (e: ActivityNotFoundException){
                Toast.makeText(view.getContext(),R.string.details_no_mps,Toast.LENGTH_LONG).show();
            }
        }
    }

}