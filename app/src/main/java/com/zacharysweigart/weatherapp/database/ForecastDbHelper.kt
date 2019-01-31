package com.zacharysweigart.weatherapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zacharysweigart.weatherapp.MainApp
import org.jetbrains.anko.db.*

class ForecastDbHelper(context: Context = MainApp.instance) :
    ManagedSQLiteOpenHelper(context, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            CityForecastTable.NAME, true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT
        )

        db.createTable(
            ForecastTable.NAME, true,
            ForecastTable.ID to INTEGER + PRIMARY_KEY,
            ForecastTable.DATE to INTEGER,
            ForecastTable.DESCRIPTION to TEXT,
            ForecastTable.TEMPERATURE to INTEGER,
            ForecastTable.ICON_URL to TEXT,
            ForecastTable.CITY_ID to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(ForecastTable.NAME, true)
        onCreate(db)
    }
}