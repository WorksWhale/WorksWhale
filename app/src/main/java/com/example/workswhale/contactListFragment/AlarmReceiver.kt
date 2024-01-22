package com.example.workswhale.contactListFragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.workswhale.ConstValues
import com.example.workswhale.IntentKeys
import com.example.workswhale.R
import com.example.workswhale.mainActivity.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra(IntentKeys.EXTRA_NAME)
        createNotification(context, name ?: "")
    }

    private fun createNotification(context: Context, name: String) {
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 26 버전 이상
            val channelId= context.getString(R.string.notification_channel_id)
            val channelName= context.getString(R.string.notification_channel_name)
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널에 다양한 정보 설정
                description = context.getString(R.string.notification_channel_description)
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(context, channelId)
        }else {
            // 26 버전 이하
            builder = NotificationCompat.Builder(context)
        }

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle(context.getString(R.string.alarm_notification_title))
            setContentText(context.getString(R.string.alarm_notification_content, name))
            addAction(R.mipmap.ic_launcher,
                context.getString(R.string.notification_action_text), pendingIntent)
            setAutoCancel(true)
        }

        manager.notify(ConstValues.NOTIFICATION_ID, builder.build())
    }
}