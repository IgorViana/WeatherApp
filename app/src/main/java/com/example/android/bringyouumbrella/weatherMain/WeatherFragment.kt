package com.example.android.bringyouumbrella.weatherMain

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.android.bringyouumbrella.R
import com.example.android.bringyouumbrella.model.WeatherResponse
import kotlinx.android.synthetic.main.fragment_weather.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private val PERMISSION_ID = 42

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        viewModel.currentWeather.observe(this, Observer { response ->
            populateView(response, view)
        })


        if (!checkPermission()) {
            requestPermissions()
        } else {
            viewModel.getWeather()
        }

        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (checkPermission()) {
                    if (isLocationEnabled()) {
                        viewModel.getWeather()
                    } else {
                        Toast.makeText(context, "Turn on location", Toast.LENGTH_LONG).show()
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(intent)
                    }
                } else {
                    requestPermissions()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //TODO TAKE THIS OUT OF FRAGMENT
    private fun populateView(response: WeatherResponse, view: View) {

        val weather = response.weather[0]

        weatherCity.text = response.name

        val convertTimeZone = convertTimeZone(response.dt)
        weatherDate.text = convertTimeZone

        val url = "https://openweathermap.org/img/wn/${weather.icon}@2x.png"
        Glide.with(view)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.findViewById(R.id.weatherIcon))

        weatherMain.text = weather.description

        val temp = "${response.main.temp.toInt()} ºC"
        weatherTemperature.text = temp

        tempMin.text = response.main.tempMin.toString()
        tempMax.text = response.main.tempMax.toString()
    }

    //TODO TAKE THIS OUT OF FRAGMENT
    private fun convertTimeZone(time: Long): String {

        val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt_br"))
        val date = Date(time * 1000)
        format.format(date)
        return date.toString()
    }

    private fun checkPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission
                (
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED &&

            ActivityCompat.checkSelfPermission
                (
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {

        ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )

    }

    private fun isLocationEnabled(): Boolean {

        val locationManager: LocationManager =
            this.activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}