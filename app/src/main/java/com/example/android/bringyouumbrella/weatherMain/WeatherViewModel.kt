package com.example.android.bringyouumbrella.weatherMain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.bringyouumbrella.model.Weather
import com.example.android.bringyouumbrella.repository.WeatherRepository
import com.example.android.bringyouumbrella.webService.RetrofitFactory
import javax.inject.Inject

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    val currentWeather: MutableLiveData<List<Weather>> = repository.currentWeather

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }

    fun getWeather() {
        repository.getWeather()
    }
}