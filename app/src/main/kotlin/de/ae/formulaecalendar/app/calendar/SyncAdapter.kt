package de.ae.formulaecalendar.app.calendar

import android.accounts.Account
import android.content.*
import android.os.Bundle
import android.util.Log
import de.ae.formulaecalendar.formulaerest.RemoteStore

/**
 * Created by aeilers on 06.09.2017.
 */
class SyncAdapter(context: Context, autoInitialize: Boolean)
    : AbstractThreadedSyncAdapter(context, autoInitialize) {

    override fun onPerformSync(account: Account, extras: Bundle, authority: String, provider: ContentProviderClient, syncResult: SyncResult) {
        //TODO do sync here
        Log.i("MyCalendar","onPerformSync")
    }

}