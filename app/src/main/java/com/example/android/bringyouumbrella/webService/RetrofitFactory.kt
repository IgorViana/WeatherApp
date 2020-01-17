package com.example.android.bringyouumbrella.webService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject


class RetrofitFactory @Inject constructor() {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

    fun weatherService(): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

}