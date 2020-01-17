package com.example.android.bringyouumbrella.webService

import com.example.android.bringyouumbrella.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "cda35fe148835e72b751e194dfeebea9"

interface WeatherService {

    @GET("weather")
    fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") api_key: String = API_KEY
    ): Observable<WeatherResponse>
}