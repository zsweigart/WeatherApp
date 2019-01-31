package com.zacharysweigart.weatherapp.detail

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zacharysweigart.weatherapp.R
import com.zacharysweigart.weatherapp.RequestDayForecastCommand
import com.zacharysweigart.weatherapp.color
import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.toDateString
import com.zacharysweigart.weatherapp.view.ToolbarManager

import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread

class DetailActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "DetailActivity.ID"
        val CITY_NAME = "DetailActivity.CityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbarMenu(this)

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread {
                bindForecast(result, baseContext)
            }
        }
    }

    private fun bindForecast(forecast: Forecast, context: Context) = with(forecast) {
        Picasso.with(context).load(iconUrl).into(icon)
        toolbarSubtitle = date.toDateString("yyyy-MM-dd HH:mm")
        weatherDescription.text = description
        bindWeather(temp to temperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(
            when (it.first) {
                in -50..25 -> android.R.color.holo_red_dark
                in 25..50 -> android.R.color.holo_orange_dark
                else -> android.R.color.holo_green_dark
            }
        )
    }
}
