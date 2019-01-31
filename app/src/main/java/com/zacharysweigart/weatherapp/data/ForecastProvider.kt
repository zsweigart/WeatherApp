package com.zacharysweigart.weatherapp.data

import com.zacharysweigart.weatherapp.database.ForecastDb
import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.domain.ForecastList
import com.zacharysweigart.weatherapp.firstResult
import com.zacharysweigart.weatherapp.request.ForecastServer

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipcode: Long): ForecastList =
        sources.firstResult { requestSourceByZip(it, zipcode) }

    fun requestById(id: Long): Forecast =
        sources.firstResult { requestSourceById(it, id) }

    private fun requestSourceByZip(source: ForecastDataSource, zipcode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipcode)
        return if (res != null && res.size > 0) res else null
    }

    private fun requestSourceById(source: ForecastDataSource, id: Long): Forecast? =
        source.requestForecastById(id)

}