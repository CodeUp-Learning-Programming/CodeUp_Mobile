package com.example.codeup.ui.composables.componentes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.codeup.MainActivity
import com.example.codeup.R

class MyNotification(private val context: Context, private val title: String, private val msg: String) {
    private val channelID: String = "FCH100"
    private val channelName: String = "FCMMessage"
    private val notificationManager =
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun fireNotification() {
        // Create Notification Channel for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Custom large icon
        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)

        // Custom sound
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.icon_estrela)  // Change to your notification icon
            .setLargeIcon(largeIcon)
            .setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setSound(soundUri)
            .setStyle(NotificationCompat.BigTextStyle().bigText(msg))  // Expandable big text
            .addAction(R.drawable.icon_fogo_azul, "Reply", pendingIntent)  // Action button
            .setVibrate(longArrayOf(0, 500, 1000))  // Vibration pattern

        // Show the notification
        notificationManager.notify(0, notificationBuilder.build())
    }
}
