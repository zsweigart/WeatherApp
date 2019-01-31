package com.zacharysweigart.weatherapp

import android.util.Log
import com.google.gson.Gson
import com.zacharysweigart.weatherapp.request.ForecastResult
import java.net.URL

internal class Request(private val zipCode: Long) {

    companion object {
        private const val APP_ID = "b80fc003fc8d068cd5bde8a8c70d352d"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast"
        private const val COMPLETE_URL = "$URL?units=imperial&appid=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val forecastJsonString = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName + "ZDS", forecastJsonString)
        return Gson().fromJson(forecastJsonString, ForecastResult::class.java)
    }
}