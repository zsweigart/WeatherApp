package com.zacharysweigart.weatherapp.data

import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.domain.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipcode: Long): ForecastList?

    fun requestForecastById(id: Long): Forecast?
}