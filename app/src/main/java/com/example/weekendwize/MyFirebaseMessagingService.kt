package com.example.weekendwize

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title ?: remoteMessage.data["title"] ?: "WeekendWize Alert"
        val message = remoteMessage.notification?.body ?: remoteMessage.data["message"] ?: "New update available!"

        Log.d("FCM_SERVICE", "Message Received: $title - $message")

        NotificationHelper.showNotification(applicationContext, title, message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_SERVICE", "Refreshed FCM Token: $token")
    }
}
