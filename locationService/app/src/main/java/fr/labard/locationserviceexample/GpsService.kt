package fr.labard.locationserviceexample

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat


class GpsService: Service(), LocationListener {

    lateinit var locationManager: LocationManager
    var locationProvider: String? = null
    lateinit var notification: Notification
    var location: Location? = null

    override fun onLocationChanged(location: Location?) {
        this.location = location
    }
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String?) {}
    override fun onProviderDisabled(provider: String?) {}

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationProvider = locationManager.getBestProvider(Criteria(), false)

        notification = NotificationCompat.Builder(this, "1")
            .setContentTitle("Recording")
            .setContentText("Tap for more info")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext, 0, Intent(
                        applicationContext,
                        MainActivity::class.java
                    ), 0
                )
            )
            .setAutoCancel(true)
            .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        this.startForeground(1, notification)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
        }
        locationManager.requestLocationUpdates(locationProvider!!, 400, 1.0f, this)
        return START_STICKY
    }
}