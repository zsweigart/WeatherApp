package com.zacharysweigart.weatherapp.request

import com.zacharysweigart.weatherapp.Request
import com.zacharysweigart.weatherapp.data.ForecastDataSource
import com.zacharysweigart.weatherapp.database.ForecastDb
import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.domain.ForecastDataMapper
import com.zacharysweigart.weatherapp.domain.ForecastList

class ForecastServer(
    private val forecastDb: ForecastDb = ForecastDb(),
    private val dataMapper: ForecastDataMapper = ForecastDataMapper()
) : ForecastDataSource {
    override fun requestForecastByZipCode(zipcode: Long): ForecastList? {
        val result = Request(zipcode).execute()
        val converted = dataMapper.convertToDomain(zipcode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipcode)
    }

    override fun requestForecastById(id: Long): Forecast? {
        throw UnsupportedOperationException("Only retreive from the local db")
    }
}