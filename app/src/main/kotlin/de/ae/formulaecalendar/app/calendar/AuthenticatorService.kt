package de.ae.formulaecalendar.app.calendar

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by aeilers on 06.09.2017.
 */
class AuthenticatorService(): Service(){
    // Instance field that stores the authenticator object
    private var mAuthenticator: Authenticator? = null

    override fun onCreate() {
        // Create a new authenticator object
        mAuthenticator = Authenticator(this)
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    override fun onBind(intent: Intent): IBinder? {
        return mAuthenticator?.iBinder
    }
}