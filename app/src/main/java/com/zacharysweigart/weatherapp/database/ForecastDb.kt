package com.zacharysweigart.weatherapp.database

import com.zacharysweigart.weatherapp.data.ForecastDataSource
import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.domain.ForecastList
import com.zacharysweigart.weatherapp.toVarargArray
import org.jetbrains.anko.db.insertOrThrow
import org.jetbrains.anko.db.select

class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: DbDataMapper = DbDataMapper()
) : ForecastDataSource {

    override fun requestForecastByZipCode(zipcode: Long) = forecastDbHelper.use {
        val dailyRequest = "${ForecastTable.CITY_ID} = ? AND " + "${ForecastTable.DATE} >= ?"
        val dailyForecast = select(ForecastTable.NAME)
            .whereSimple(dailyRequest, zipcode.toString(), System.currentTimeMillis().toString())
            .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipcode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    override fun requestForecastById(id: Long): Forecast? = forecastDbHelper.use {
        val request = "${ForecastTable.ID} = ?"
        val result = select(ForecastTable.NAME)
            .whereSimple(request, id.toString())
            .parseOpt { DayForecast(HashMap(it)) }

        if (result != null) dataMapper.convertDayToDomain(result) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(ForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insertOrThrow(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insertOrThrow(ForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}