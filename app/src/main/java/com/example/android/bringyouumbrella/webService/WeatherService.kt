package com.example.android.bringyouumbrella.webService

import com.example.android.bringyouumbrella.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "cda35fe148835e72b751e194dfeebea9"
const val UNITS = "metric"
const val LANGUAGE = "pt_br"

//https://openweathermap.org/data/2.5/weather/?lat=35&lon=139&units=metric&lang=pt_br&appid=b6907d289e10d714a6e88b30761fae22

interface WeatherService {

    @GET("weather")
    fun getCityWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = UNITS,
        @Query("lang") language: String = LANGUAGE,
        @Query("appid") api_key: String = API_KEY
    ): Observable<WeatherResponse>
}