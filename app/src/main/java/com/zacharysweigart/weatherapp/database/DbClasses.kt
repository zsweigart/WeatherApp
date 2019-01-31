package com.zacharysweigart.weatherapp.database

class CityForecast(var map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}

class DayForecast(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var date: Long by map
    var description: String by map
    var temp: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, temp: Int, iconUrl: String, cityId: Long)
            : this(HashMap()) {
        this.date = date
        this.description = description
        this.temp = temp
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}