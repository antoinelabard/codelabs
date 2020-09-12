package com.example.locationserviceexample

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

//    var isGPSEnabled = false
//
//    var isNetworkEnabled = false
//
//    var canGetLocation = false
////    var location: Location? = null
//
//    protected var locationManager: LocationManager? = null
//    fun getLocation(): Location? {
//        try {
//            locationManager = mContext.getSystemService(LOCATION_SERVICE) as LocationManager
//            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
//            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//
//            if (isGPSEnabled || isNetworkEnabled) {
//                canGetLocation = true
//                if (isNetworkEnabled) {
//                    if (ActivityCompat.checkSelfPermission(
//                            this,
//                            Manifest.permission.ACCESS_FINE_LOCATION
//                        ) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(
//                            this,
//                            Manifest.permission.ACCESS_COARSE_LOCATION
//                        ) != PackageManager.PERMISSION_GRANTED
//                    ) {
//                        return null
//                    }
//                    locationManager!!.requestLocationUpdates(
//                        LocationManager.NETWORK_PROVIDER,
//                        MIN_TIME_BW_UPDATES,
//                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
//                    )
//                }
//
//                if (isGPSEnabled) {
//                    if (location == null) {
//                        locationManager!!.requestLocationUpdates(
//                            LocationManager.GPS_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
//                        )
//                        Log.d("GPS Enabled", "GPS Enabled")
//                        if (locationManager != null) {
//                            location = locationManager!!
//                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
//                        }
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return location
//    }
//
//    fun stopUsingGPS() {
//        if (locationManager != null) {
//            locationManager!!.removeUpdates(this@GPSTracker)
//        }
//    }
//
//    fun showSettingsAlert() {
//        val alertDialog = AlertDialog.Builder(mContext)
//
//        // Setting Dialog Title
//        alertDialog.setTitle("GPS is settings")
//
//        // Setting Dialog Message
//        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?")
//
//        // On pressing Settings button
//        alertDialog.setPositiveButton("Settings") { dialog, which ->
//            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//            mContext.startActivity(intent)
//        }
//
//        // on pressing cancel button
//        alertDialog.setNegativeButton(
//            "Cancel"
//        ) { dialog, which -> dialog.cancel() }
//
//        // Showing Alert Message
//        alertDialog.show()
//    }
//
//    override fun onBind(arg0: Intent): IBinder? {
//        return null
//    }
//
//    companion object {
//        // The minimum distance to change Updates in meters
//        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters
//
//        // The minimum time between updates in milliseconds
//        private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1 // 1 minute
//            .toLong()
//    }
//
//    init {
//        getLocation()
//    }
}