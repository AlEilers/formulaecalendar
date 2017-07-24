package de.ae.formulaecalendar.app.notification

import android.app.*
import android.app.Notification.CATEGORY_ALARM
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.NotificationCompat
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

        var notification: Notification

        // For >Android O use notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //create and set notification channel
            val notificationChannel = this.createNotificationChannel()
            notificationManager.createNotificationChannel(notificationChannel)
            //create notification
            notification = this.createNotification(title, content, notificationChannel.id)
        } else {
            //create notification
            notification = this.createNotification(title, content)
        }

        //show Notification
        notificationManager.notify(id, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        // Create the notification channel
        val id = getString(R.string.noti_channel_id)
        val name = getString(R.string.noti_channel_name)
        val description = getString(R.string.noti_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(id, name, importance)
        // Configure the notification channel.
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = getColor(R.color.colorPrimaryDark)
        mChannel.enableVibration(true)
        //mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        return mChannel
    }

    // Use Notification Compat for SDK Version < Android O
    private fun createNotification(title: String, content: String): Notification {
        val newIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_notification)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(CATEGORY_ALARM)
        }

        val notification = builder.build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        return notification
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(title: String, content: String, channelId: String): Notification {
        val newIntent = Intent(this, DetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = Notification.Builder(this, channelId)
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