package fr.labard.serviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat


class SimpleService : Service() {

    val CHANNEL_ID = "1"

    override fun onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Creating Notification", Toast.LENGTH_SHORT).show()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Service title")
            .setTicker("Service ticker")
            .setContentText("Content text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true).build()
        startForeground(1001, notification)
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}