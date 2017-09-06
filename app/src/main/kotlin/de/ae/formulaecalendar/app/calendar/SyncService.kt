package de.ae.formulaecalendar.app.calendar

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Service
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log

/**
 * Created by aeilers on 06.09.2017.
 */
class SyncService : Service() {
    companion object {
        val ACCOUNT_TYPE = "de.ae.formulaecalendar.app"
        val ACCOUNT = "FormulaERestAccount"
        val AUTHORITY = "com.android.calendar"
        val SYNC_INTERVAL_IN_SECONDS: Long = 1 * 24 * 60 * 60 // 1d * 24h/d * 60min/h * 60s/min

        private val syncAdapterLock = Any()
        private var syncAdapter: SyncAdapter? = null

        fun createSyncAccount(context: Context): Account {

            val newAccount = Account(ACCOUNT, ACCOUNT_TYPE)
            val accountManager = context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

            if (accountManager.addAccountExplicitly(newAccount, null, null)) {

            } else {
                Log.w("SyncAdapter", "Cannot create account")
            }

            return newAccount
        }

        fun addPeriodicSync(context: Context) {
            ContentResolver.addPeriodicSync(createSyncAccount(context), AUTHORITY, Bundle.EMPTY, SYNC_INTERVAL_IN_SECONDS)
        }

        fun syncNow(context: Context){
            val account = createSyncAccount(context)
            val settingsBundle = Bundle()
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true)
            settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true)
            ContentResolver.requestSync(account, SyncService.AUTHORITY, settingsBundle)
        }
    }

    override fun onCreate() {
        super.onCreate()

        synchronized(syncAdapterLock) {
            if (syncAdapter == null) {
                syncAdapter = SyncAdapter(applicationContext, true)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return syncAdapter?.syncAdapterBinder
    }
}