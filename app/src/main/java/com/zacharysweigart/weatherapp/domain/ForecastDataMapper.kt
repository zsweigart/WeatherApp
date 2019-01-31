package com.zacharysweigart.weatherapp.domain

import com.zacharysweigart.weatherapp.request.Forecast
import com.zacharysweigart.weatherapp.request.ForecastResult
import java.util.*
import java.util.concurrent.TimeUnit
import com.zacharysweigart.weatherapp.domain.Forecast as ForecastModel


class ForecastDataMapper {
    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ForecastModel> {
        return list.mapIndexed { i, forecast ->
            convertForecastItemToDomain(
                forecast.copy(
                    dt = TimeUnit.MILLISECONDS.convert(
                        forecast.dt,
                        TimeUnit.SECONDS
                    ) + TimeZone.getDefault().getOffset(Date().time)
                )
            )
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ForecastModel(
            -1, dt, weather[0].description, main.temp.toInt(), generateIconUrl(weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}