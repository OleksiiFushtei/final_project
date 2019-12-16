package com.example.final_project.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.final_project.ListOfControllersActivity
import com.example.final_project.R
import com.example.final_project.api.interfaces.FirebaseInteface
import com.example.final_project.models.ErrorModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.orhanobut.hawk.Hawk


class MyFirebaseMessagingService :
    FirebaseMessagingService(),
    FirebaseInteface.PostTokenListener {

    override fun onMessageReceived(
        p0: RemoteMessage
    ) {
        super.onMessageReceived(
            p0
        )
        val from =
            p0.from
        Log.i(
            "firebase",
            "Message received from: $from"
        )
        if (p0.notification != null) {
            Log.i(
                "firebase",
                "Notification: " + p0.notification!!.body.toString()
            )
            Log.i(
                "firebase",
                p0.notification!!.channelId.toString()
            )

            sendNotification(
                p0.notification?.title,
                p0.notification?.body
            )
        }
        if (p0.data.isNotEmpty()) {
            Log.i(
                "firebase",
                "Data: " + p0.data.toString()
            )
        }
    }

    private fun sendNotification(
        title: String?,
        body: String?
    ) {
        val notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        val intent =
            Intent(
                this,
                ListOfControllersActivity::class.java
            )
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT
            )
        val soundUri =
            RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationBuilder =
                NotificationCompat.Builder(
                    this
                )
                    .setSmallIcon(
                        R.drawable.ic_notifications_active_black_48dp
                    )
                    .setContentTitle(
                        title
                    )
                    .setContentText(
                        body
                    )
                    .setAutoCancel(
                        true
                    )
                    .setSound(
                        soundUri
                    )
                    .setContentIntent(
                        pendingIntent
                    )
                    .setChannelId(
                        notificationManager.getNotificationChannel(
                            "10001"
                        ).id
                    )

            notificationManager.notify(
                1,
                notificationBuilder.build()
            )
        } else {
            val notificationBuilder =
                NotificationCompat.Builder(
                    this
                )
                    .setSmallIcon(
                        R.drawable.ic_notifications_active_black_48dp
                    )
                    .setContentTitle(
                        title
                    )
                    .setContentText(
                        body
                    )
                    .setAutoCancel(
                        true
                    )
                    .setSound(
                        soundUri
                    )
                    .setContentIntent(
                        pendingIntent
                    )

            notificationManager.notify(
                1,
                notificationBuilder.build()
            )
        }
    }

    override fun onNewToken(
        p0: String
    ) {
        super.onNewToken(
            p0
        )
        Log.i(
            "firebase",
            "Token: $p0"
        )
        if (!Hawk.contains(
                "firebaseToken"
            )
        ) {
            Hawk.put(
                "firebaseToken",
                p0
            )
        }
    }

    override fun onPostTokenResponseSuccess(
        token: String
    ) {
        Hawk.put(
            "firebaseToken",
            token
        )
    }

    override fun onPostTokenResponseFailure(
        errorModel: ErrorModel?
    ) {

    }

    override fun onPostTokenCancelled() {

    }

    override fun onPostTokenFailure() {

    }

}