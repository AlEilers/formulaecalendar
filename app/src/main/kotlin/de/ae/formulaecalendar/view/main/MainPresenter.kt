package de.ae.formulaecalendar.view.main

import de.ae.formulaecalendar.remote.DataStore
import de.ae.formulaecalendar.remote.RemoteStore
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.app.Activity
import android.net.Uri
import de.ae.formulaecalendar.R


/**
 * Created by alexa on 17.02.2017.
 */
class MainPresenter constructor(val view: MainView, val model: DataStore, val observer: Scheduler, val subscriber: Scheduler) {

    constructor(view: MainView) : this(view, RemoteStore, AndroidSchedulers.mainThread(), Schedulers.newThread())

    fun giveFeedback(activity: Activity) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", activity.getString(R.string.feedback_email), null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.feedback_subject))
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        activity.startActivity(Intent.createChooser(emailIntent, activity.getString(R.string.title_send_feedback)))
    }
}