package com.zacharysweigart.weatherapp.domain

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int): Forecast = dailyForecast[position]
}

data class Forecast(val id: Long, val date: Long, val description: String, val temp: Int, val iconUrl: String)