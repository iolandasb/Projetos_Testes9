package com.example.projetointegrador.presentation


/*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseToken : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TokenFirebase", "Refreshed token: $token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificationManager = baseContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel("com.example.projetointegrador.presentation", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(this)
            //.setSmallIcon(BitmapFactory)
            .setContentTitle("Título" + p0.notification?.title)
            .setContentText(p0.notification?.body)
            .build()

        notificationManager.notify(0, notification)
    }

}
*/