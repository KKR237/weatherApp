package com.keyur.weatherapp
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class HomeScreen : AppCompatActivity() {
    // inside a basic activity
    private var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var locationBtn = findViewById<Button>(R.id.btnGetLcn)

        // Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        locationBtn.setOnClickListener {view ->
            try {
                // Request location updates
                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            } catch(ex: SecurityException) {
                Log.d("Alert", "Security Exception, no location available")
            }
        }
    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        var txtLocation = findViewById<TextView>(R.id.txtlocation)
        override fun onLocationChanged(location: Location) {
            txtLocation.text = ("" + location.longitude + ":" + location.latitude)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}