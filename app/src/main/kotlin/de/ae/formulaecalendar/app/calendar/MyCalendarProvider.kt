package de.ae.formulaecalendar.app.calendar

import android.Manifest
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.preference.PreferenceManager
import android.provider.CalendarContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.format.Time.TIMEZONE_UTC
import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.formulaerest.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.formulaerest.pojo.calendar.RaceCalendarData
import de.ae.formulaecalendar.formulaerest.pojo.calendar.isRaceNameAvailable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by aeilers on 19.02.2017.
 */
class MyCalendarProvider(context: Context) : Observer<RaceCalendarData?> {
    private val prefCalendar = "pref_calendar_enabled"

    private val cr: ContentResolver
    private val calendarColor: Int
    private val accountName: String
    private val calendarName: String
    private val roundTitle: String
    private var calendarEnabled: Boolean = false

    private val allRaces: MutableList<CalendarDatum> = mutableListOf()

    init {
        this.cr = context.contentResolver
        calendarColor = ContextCompat.getColor(context, R.color.colorPrimary)
        this.accountName = context.getString(R.string.cal_account_name)
        this.calendarName = context.getString(R.string.cal_calendar_name)
        this.roundTitle = context.getString(R.string.cal_event_round)

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        calendarEnabled = prefs.getBoolean(prefCalendar, false)
    }

    fun manageCalendar(context: Context, obs: Observable<RaceCalendarData?>) {

        //check permission
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            Log.w("MyCalendarProvider", "Cannot create calendar: Permission not granted")
            return
        }

        if (calendarEnabled) {
            obs.subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.newThread())
                    .subscribe(this)
        } else {
            val calId = this.getCalId()
            if (calId > 0) { //if calendar is disabled but exists
                val uri = ContentUris.withAppendedId(CalendarContract.Calendars.CONTENT_URI, calId.toLong())
                this.deleteCalendar(uri)
            }
        }
    }

    override fun onSubscribe(d: Disposable?) {
        allRaces.clear()
    }

    override fun onComplete() {
        var id = this.getCalId()
        if (id == 0) {  //if calendar does not exist, create it
            insertCalendar()
            id = getCalId()
        } else {  //if calendar already exists, delete all events in it
            deleteAllEvents(id)
        }

        //insert new events
        insertRaces(id, allRaces)
    }

    override fun onError(t: Throwable) {
        Log.w("MyCalendarProvider", "Cannot create calendar: ${t.message}")
    }

    override fun onNext(raceCalendarData: RaceCalendarData?) {
        raceCalendarData?.calendarData?.let { allRaces.addAll(it) }
    }

    /**
     * @return id of calendar, 0 if calendar not found or permission not granted
     */
    private fun getCalId(): Int {

        //find calendar
        val projection = arrayOf(CalendarContract.Calendars._ID, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CalendarContract.Calendars.ACCOUNT_NAME)
        val where = projection[1] + "='" + this.calendarName + "' AND " + projection[2] + "='" + this.accountName + "'"
        val cursor: Cursor? = cr.query(CalendarContract.Calendars.CONTENT_URI, projection, where, null, null)

        var calId = 0
        return cursor?.let {

            val idCol = cursor.getColumnIndex(projection[0])
            if (cursor.moveToFirst()) {
                calId = Integer.parseInt(cursor.getString(idCol))
            }
            cursor.close()

            calId
        } ?: calId
    }


    private fun insertRaces(calId: Int, races: List<CalendarDatum>) {
        val description = "$roundTitle "

        for (race in races) {
            val start = race.raceDate?.time ?: 0
            val end = start + 24 * 60 * 60 * 1000
            val eventname =
                    if (race.isRaceNameAvailable()) {
                        race.raceName
                    } else {
                        race.city
                    } ?: "?"
            insertEvent(calId, start, end, eventname, description + race.sequence)
        }
    }

    private fun insertCalendar(): Uri? {
        val values = ContentValues()
        values.put(CalendarContract.Calendars.ACCOUNT_NAME, accountName)
        values.put(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL)
        values.put(CalendarContract.Calendars.NAME, calendarName)
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, calendarName)
        values.put(CalendarContract.Calendars.CALENDAR_COLOR, calendarColor)
        values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER)
        values.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, TIMEZONE_UTC)
        values.put(CalendarContract.Calendars.SYNC_EVENTS, 1)

        val builder = CalendarContract.Calendars.CONTENT_URI.buildUpon()
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, accountName)
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL)
        builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")

        return cr.insert(builder.build(), values)
    }

    private fun deleteCalendar(uri: Uri) {
        cr.delete(uri, null, null)
    }

    private fun insertEvent(calId: Int, start: Long, end: Long, title: String, description: String): Uri? {
        val values = ContentValues()
        values.put(CalendarContract.Events.ALL_DAY, 1)
        values.put(CalendarContract.Events.DTSTART, start)
        values.put(CalendarContract.Events.DTEND, end)
        values.put(CalendarContract.Events.TITLE, title)
        values.put(CalendarContract.Events.DESCRIPTION, description)
        values.put(CalendarContract.Events.CALENDAR_ID, calId)
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TIMEZONE_UTC)

        return cr.insert(CalendarContract.Events.CONTENT_URI, values)
    }

    /**
     *
     * @param calId the calendar which events should be deleted
     */
    private fun deleteAllEvents(calId: Int) {

        val projection = arrayOf(CalendarContract.Events._ID, CalendarContract.Events.CALENDAR_ID)
        val where = projection[1] + "=" + calId

        val cursor: Cursor? = cr.query(CalendarContract.Events.CONTENT_URI, projection, where, null, null)

        cursor?.let {
            val idCol = cursor.getColumnIndex(projection[0])
            if (cursor.moveToFirst()) {
                do {
                    val eventId = Integer.parseInt(cursor.getString(idCol))
                    val eventUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventId.toLong())
                    cr.delete(eventUri, null, null)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}