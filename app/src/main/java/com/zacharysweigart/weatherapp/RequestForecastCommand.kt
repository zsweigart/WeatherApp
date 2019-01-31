package com.zacharysweigart.weatherapp

import com.zacharysweigart.weatherapp.data.ForecastProvider
import com.zacharysweigart.weatherapp.domain.Command
import com.zacharysweigart.weatherapp.domain.ForecastList

class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<ForecastList> {
    override fun execute(): ForecastList =
        forecastProvider.requestByZipCode(zipCode)
}