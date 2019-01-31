package com.zacharysweigart.weatherapp

import com.zacharysweigart.weatherapp.data.ForecastProvider
import com.zacharysweigart.weatherapp.domain.Command
import com.zacharysweigart.weatherapp.domain.Forecast

class RequestDayForecastCommand(
    val id: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<Forecast> {
    override fun execute(): Forecast =
        forecastProvider.requestById(id)
}