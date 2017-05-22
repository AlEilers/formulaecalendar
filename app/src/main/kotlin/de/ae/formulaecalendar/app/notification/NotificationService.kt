package de.ae.formulaecalendar.app.notification

import android.app.IntentService
import android.app.Notification
import android.app.Notification.CATEGORY_ALARM
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import de.ae.formulaecalendar.app.R
import de.ae.formulaecalendar.app.view.details.DetailsActivity

/**
 * Created by aeilers on 18.02.2017.
 */
class NotificationService : IntentService("NotificationService") {


    override fun onHandleIntent(intent: Intent?) {

        val title = intent?.getStringExtra("NOTIFICATION_TITLE") ?: getString(R.string.noti_no_title)
        val content = intent?.getStringExtra("NOTIFICATION_CONTENT") ?: ""
        val id = intent?.getIntExtra("NOTIFICATION_ID", 0) ?: 0

        showNotification(title, content, id)
    }

    private fun showNotification(title: String, content: String, id: Int) {
        val newIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_notification)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(CATEGORY_ALARM)
        }

        val notification = builder.build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        //show Notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id, notification)
    }
}