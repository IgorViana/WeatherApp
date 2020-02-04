package com.example.android.bringyouumbrella.weatherMain

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.bringyouumbrella.model.WeatherResponse
import com.example.android.bringyouumbrella.repository.WeatherRepository
import com.google.android.gms.location.*

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository = WeatherRepository()
    private val fusedLocationClient: FusedLocationProviderClient

    val currentWeather: MutableLiveData<WeatherResponse> = repository.currentWeather

    private var mLatitude : Double = -19.920
    private var mLongitude : Double = -43.9378

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }

    fun getWeather() {
        getLastKnownLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                mLatitude = location.latitude
                mLongitude = location.longitude
                repository.getWeather(mLatitude, mLongitude)
            }else{
                requestNewLocationData()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.getMainLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult) {

            val mLastLocation: Location = locationResult.lastLocation
            mLatitude = mLastLocation.latitude
            mLongitude = mLastLocation.longitude
            repository.getWeather(mLatitude, mLongitude)
        }
    }


}