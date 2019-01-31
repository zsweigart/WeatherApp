package com.zacharysweigart.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.zacharysweigart.weatherapp.detail.DetailActivity
import com.zacharysweigart.weatherapp.settings.SettingsActivity
import com.zacharysweigart.weatherapp.view.ToolbarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import com.zacharysweigart.weatherapp.domain.Forecast as ForecastModel

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    private val zipCode: Long by DelegatesExtensions.preference(
        this, SettingsActivity.ZIP_CODE,
        SettingsActivity.DEFAULT_ZIP
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbarMenu(this)

        forecast_list.layoutManager = LinearLayoutManager(this)

        attachToScroll(forecast_list)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            forecast_list.adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to "${result.city}, ${result.country}"
                )
            }

            toolbarTitle = "${result.city}, ${result.country}"
        }
    }
}
