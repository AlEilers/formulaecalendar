package de.ae.formulaecalendar.app.view.main

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.calendar.MyCalendarProvider
import de.ae.formulaecalendar.app.notification.NotificationReceiver
import de.ae.formulaecalendar.formulaerest.DataStore
import de.ae.formulaecalendar.formulaerest.RemoteStore
import de.ae.formulaecalendar.formulaerest.pojo.series.ChampsDatum
import io.reactivex.MaybeObserver
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by aeilers on 17.02.2017.
 */
class MainPresenter constructor(val view: MainView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: MainView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun loadContent() {
        model.getCurrentChampionShip()
                .subscribeOn(subscriber) // Create a new Thread
                .observeOn(observer) // Use the UI thread
                .subscribe(object : MaybeObserver<ChampsDatum?> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onComplete() {

                    }

                    override fun onError(t: Throwable) {
                        Log.w("MainPresenter", "Cannot load view: ${t.message}")
                    }

                    override fun onSuccess(champsDatum: ChampsDatum?) {
                        var title: String? = champsDatum?.championship
                        if (title != null) {
                            title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase()
                            view.setTitle(title)
                        } else {
                            Log.w("MainPresenter", "Cannot load view: title is null")
                        }
                    }
                })
    }

    fun giveFeedback(activity: Activity) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", activity.getString(R.string.feedback_email), null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.feedback_subject))
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        activity.startActivity(Intent.createChooser(emailIntent, activity.getString(R.string.title_send_feedback)))
    }

    fun openPlayStore(activity: Activity) {
        val uri = Uri.parse("market://details?id=" + activity.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            activity.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.packageName)))
        }
    }

    fun manageCalendar(activity: Activity) {
        MyCalendarProvider(activity).manageCalendar(activity, model.getCurrentRaceCalendar())
    }

    fun scheduleNotifications(activity: Activity) {
        NotificationReceiver().scheduleNotifications(activity, model.getCurrentRaceCalendar())
    }
}