package com.example.osmdroidexample

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.Polyline
import kotlin.math.abs
import kotlin.random.Random
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.overlay.*
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MainActivity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS_REQUEST_CODE: Int = 1
    val LON_MIN = 1.0
    val LAT_MIN = 50.0
    val LON_MAX = 1.0
    val LAT_MAX = 50.0
    val NB_POINTS = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(applicationContext, getPreferences(Context.MODE_PRIVATE))

        setContentView(R.layout.activity_main)

        map.setTileSource(TileSourceFactory.MAPNIK)
        requestPermissionsIfNecessary(arrayOf(
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ))
        map.setMultiTouchControls(true)
        val mapController = map.controller
        // Set the default zoom
        mapController.setZoom(13.0)
        // Set start point
        val startPoint = GeoPoint(40.470859, 1.079186)
        mapController.setCenter(startPoint)
        // Add a compass
        val mCompassOverlay = CompassOverlay(this, InternalCompassOrientationProvider(this), map)
        mCompassOverlay.enableCompass()
        map.overlays.add(mCompassOverlay)

        //your items
        val items = mutableListOf<OverlayItem?>()
        items.add(OverlayItem("My home", "This is my home!", GeoPoint(49.473590, 1.087810))) // Lat/Lon decimal degrees

        //the overlay
        val mOverlay = ItemizedIconOverlay(
            items,
            object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem?> {
                override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                    Toast.makeText(this@MainActivity, "you taped my home!", Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                    Toast.makeText(this@MainActivity, "you long pressed my home!", Toast.LENGTH_LONG).show()
                    return false
                }
            }, this
        )
        map.overlays.add(mOverlay)
        //marker
        val startMarker = Marker(map)
        startMarker.position = startPoint
        startMarker.setAnchor(
            Marker.ANCHOR_CENTER,
            Marker.ANCHOR_BOTTOM
        )
        map.overlays.add(startMarker)
        startMarker.icon = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
        startMarker.title = "Start point"
        //series of points
        val geoPoints = getRandomArrayOfGeopoints(
            LON_MIN,
            LAT_MIN,
            LON_MAX,
            LAT_MAX,
            NB_POINTS
        )
        //draw a line linking all the points of the array
        val line = Polyline()
        line.setPoints(geoPoints)
        line.setOnClickListener { polyline, mapView, eventPos ->
            Toast.makeText(
                mapView.context,
                "polyline with " + polyline.actualPoints.size.toString() + "pts was tapped",
                Toast.LENGTH_LONG
            ).show()
            false
        }
        map.overlayManager.add(line)

        val geoPoints2 = getRandomArrayOfGeopoints(
            LON_MIN,
            LAT_MIN,
            LON_MAX,
            LAT_MAX,
            NB_POINTS
        )
        // Draw a polygon linking each point of the array
        val polygon = Polygon()
        geoPoints2.add(geoPoints2[0])
        polygon.points = geoPoints2
        polygon.title = "A sample polygon"
        map.overlayManager.add(polygon)

        val mMyLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), map)
        //val mapController = map.controller // Already declared
        mMyLocationOverlay.disableMyLocation()
        mMyLocationOverlay.disableFollowLocation()
        mMyLocationOverlay.isDrawAccuracyEnabled = true
        mMyLocationOverlay.runOnFirstFix {
            runOnUiThread {
                mapController.animateTo(mMyLocationOverlay.myLocation)
                mapController.setZoom(18.0)
            }
        }
        map.overlays.add(mMyLocationOverlay)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val permissionsToRequest = arrayListOf<String>()
        for (element in grantResults) {
            permissionsToRequest.add(element.toString())
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toArray(arrayOf<String>()),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }

    }

    fun requestPermissionsIfNecessary(permissions: Array<String>) {
        val permissionsToRequest: ArrayList<String> = arrayListOf()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toArray(arrayOf<String>()),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }
}

fun getRandomArrayOfGeopoints(
    LON_MIN: Double,
    LAT_MIN: Double,
    LON_MAX: Double,
    LAT_MAX: Double,
    NB_POINTS: Int): MutableList<GeoPoint> {
    //Random(0)
    val a = mutableListOf<GeoPoint>()
    for (i in 1..abs(NB_POINTS)) {
        a.add(
            GeoPoint(
                (Random.nextDouble() * (LAT_MAX - LAT_MIN)) + LAT_MIN,
                (Random.nextDouble() * (LON_MAX - LON_MIN)) + LON_MIN
            )
        )
    }
    println(a.toString())
    return a
}
