package com.zacharysweigart.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zacharysweigart.weatherapp.domain.Forecast
import com.zacharysweigart.weatherapp.domain.ForecastList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.*

internal class ForecastListAdapter(
    private val weekForecast: ForecastList,
    private val onItemClickListener: (Forecast) -> Unit
) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false),
        onItemClickListener
    )

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weekForecast[position])
    }

    class ViewHolder(private val forecastView: View, val itemClick: (Forecast) -> Unit) :
        RecyclerView.ViewHolder(forecastView), LayoutContainer {

        override val containerView: View?
            get() = forecastView

        fun bind(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(icon_view)
                date_view.text = date.toDateString("yyyy-MM-dd HH:mm")
                description_view.text = description
                temperature_view.text = "$temp"
                forecast_view.setOnClickListener { itemClick(this) }
            }
        }
    }
}