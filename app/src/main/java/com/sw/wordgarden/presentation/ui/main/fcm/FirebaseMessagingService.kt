package com.sw.wordgarden.presentation.ui.main.fcm

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.GetUidForServiceUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCase
import com.sw.wordgarden.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "FirebaseMessagingService"

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var updateFcmTokenUseCase: UpdateFcmTokenUseCase

    @Inject
    lateinit var getUidForServiceUseCase: GetUidForServiceUseCase

    private var uid = ""
    private var isRunning = false

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(TAG, "Refreshed token: $token")
        if (isRunning) {
            getUid(token)
        } else {
            isRunning = true
        }
    }

    private fun getUid(token: String) {
        serviceScope.launch {
            runCatching {
                uid = getUidForServiceUseCase() ?: ""
            }.onFailure {
                Log.e(TAG, "Failed to get uid", it)
            }.onSuccess {
                if (uid.isNotBlank()) {
                    updateFcmToken(token)
                }
            }
        }
    }

    private fun updateFcmToken(token: String) {
        Log.i(TAG, "Request sending updated token to server")

        serviceScope.launch {
            runCatching {
                updateFcmTokenUseCase(token)
            }.onFailure {
                Log.e(TAG, "Failed to update fcm token", it)
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            if (isLongRunningJob()) {
                scheduleJob()
            } else {
                handleNow()
            }
        } else {
            Log.e(TAG, "data is empty. can't receive message")
        }

        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            it.body?.let { body -> sendNotification(body) }
        }
    }

    private fun isLongRunningJob() = true

    private fun scheduleJob() {
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).beginWith(work).enqueue()
    }

    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun sendNotification(messageBody: String) {
        val requestCode = 0
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.img_splash)
            .setContentTitle(getString(R.string.push_share_quiz_title))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Word Garden Channel",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationId = 0
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    suspend fun getFirebaseToken(): String? {
        return try {
            FirebaseMessaging.getInstance().deleteToken().await()
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get Token", e)
            null
        }
    }

    internal class MyWorker(appContext: Context, workerParams: WorkerParameters) :
        Worker(appContext, workerParams) {
        override fun doWork(): Result {
            return Result.success()
        }
    }
}
