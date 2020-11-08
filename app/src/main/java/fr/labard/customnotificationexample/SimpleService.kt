package fr.labard.customnotificationexample

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class SimpleService : Service() {

    val CHANNEL_ID = "1"

    lateinit var playPendingIntent: PendingIntent
    lateinit var stopPendingIntent: PendingIntent

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {

        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val playIntent = Intent(this, SimpleService::class.java)
            .setAction(if (intent.action == "pause") "play" else "pause")
        playPendingIntent = PendingIntent.getForegroundService(this, 1, playIntent, 0)
        val stopIntent = Intent(this, SimpleService::class.java)
            .setAction("stop")
        stopPendingIntent = PendingIntent.getForegroundService(this, 1, stopIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(R.drawable.ic_baseline_stop_24, "stop", stopPendingIntent)

        when (intent.action) {
            "stop" -> stopSelf()
            "pause" -> {
                notification.addAction(R.drawable.ic_baseline_play_24, "play", playPendingIntent)
                Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show()

            }
            else -> {
                notification.addAction(R.drawable.ic_baseline_pause_24, "pause", playPendingIntent)
                Toast.makeText(this, "play", Toast.LENGTH_SHORT).show()

            }
        }
        startForeground(1001, notification.build())
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}