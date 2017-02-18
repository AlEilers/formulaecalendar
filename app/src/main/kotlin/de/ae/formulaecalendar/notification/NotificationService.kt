package de.ae.formulaecalendar.notification

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.jakewharton.threetenabp.AndroidThreeTen
import de.ae.formulaecalendar.R
import de.ae.formulaecalendar.remote.pojo.calendar.CalendarDatum
import de.ae.formulaecalendar.view.details.DetailsActivity
import org.threeten.bp.format.DateTimeFormatter

import android.app.Notification.CATEGORY_ALARM

/**
 * Created by alexa on 18.02.2017.
 */
class NotificationService: IntentService("NotificationService") {

    override fun onHandleIntent(intent: Intent?) {

        //initialize ThreeTen
        AndroidThreeTen.init(this)

        val race = intent!!.getSerializableExtra("race") as CalendarDatum ?: return

        //create Notification
        val format = getString(R.string.format_date) + ' ' + getString(R.string.format_time)
        val time = race.raceStart.format(DateTimeFormatter.ofPattern(format))

        val newIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = Notification.Builder(this)
                .setContentTitle(race.raceName)
                .setContentText(String.format(getString(R.string.noti_content), time))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.icon_bw)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(CATEGORY_ALARM)
        }

        val notification = builder.build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        //show Notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(race.hashCode(), notification)
    }
}