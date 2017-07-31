package de.ae.formulaecalendar.app.notification

import android.app.*
import android.app.Notification.CATEGORY_ALARM
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
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
        //get Notification Manager
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = getString(R.string.noti_channel_id)

        //create and set notification channel (For Android >O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = this.createNotificationChannel(channelId)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        //create and show notification
        val notification = this.createNotification(title, content, channelId)
        notificationManager.notify(id, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String): NotificationChannel {
        // Create the notification channel
        val name = getString(R.string.noti_channel_name)
        val description = getString(R.string.noti_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(id, name, importance)
        // Configure the notification channel.
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        mChannel.enableVibration(true)
        //mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        return mChannel
    }

    private fun createNotification(title: String, content: String, channelId: String): Notification {
        val newIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_notification)
                .setCategory(CATEGORY_ALARM)
                .build()

        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        return notification
    }
}