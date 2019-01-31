package com.zacharysweigart.weatherapp.database

object CityForecastTable {
    const val NAME = "CityForecast"
    const val ID = "_id"
    const val CITY = "city"
    const val COUNTRY = "country"
}

object ForecastTable {
    const val NAME = "Forecast"
    const val ID = "_id"
    const val DATE = "date"
    const val DESCRIPTION = "description"
    const val TEMPERATURE = "temp"
    const val ICON_URL = "iconUrl"
    const val CITY_ID = "cityId"
}
