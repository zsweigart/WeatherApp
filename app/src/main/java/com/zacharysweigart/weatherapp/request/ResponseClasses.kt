package com.zacharysweigart.weatherapp.request

import com.google.gson.annotations.SerializedName

data class ForecastResult(val city: City, val list: List<Forecast>)

data class City(
    val id: Long, val name: String, val coord: Coordinates, val country: String,
    val population: Int
)

data class Coordinates(val lon: Float, val lat: Float)

data class Forecast(
    val dt: Long, val main: Main, val pressure: Float, val humidity: Int,
    val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Clouds,
    val rain: Rain?
)

data class Main(
    val temp: Float, val temp_min: Float, val temp_max: Float
)

data class Weather(val id: Long, val main: String, val description: String, val icon: String)

data class Clouds(val all: String)

data class Rain(@SerializedName("3h") val chance: Float)