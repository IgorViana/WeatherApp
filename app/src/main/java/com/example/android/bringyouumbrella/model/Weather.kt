package com.example.android.bringyouumbrella.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Clouds(val all: Int)

data class Sys(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)