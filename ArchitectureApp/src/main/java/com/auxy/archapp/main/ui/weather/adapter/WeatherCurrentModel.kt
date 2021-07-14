package com.auxy.archapp.main.ui.weather.adapter

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.auxy.archapp.R

@EpoxyModelClass(layout = R.layout.item_weather_current)
abstract class WeatherCurrentModel : EpoxyModelWithHolder<WeatherCurrentHolder>() {
    @EpoxyAttribute
    var summaryText: String? = null

    @EpoxyAttribute
    var timeText: String? = null

    @EpoxyAttribute
    var temperatureText: String? = null

    override fun bind(holder: WeatherCurrentHolder) {
        super.bind(holder)
        with(holder.viewBinding) {
            currentSummary.text = summaryText
            dateTime.text = timeText
            temperature.text = temperatureText
        }
    }

    override fun unbind(holder: WeatherCurrentHolder) {
        super.unbind(holder)
        with(holder.viewBinding) {
            currentSummary.text = summaryText
            dateTime.text = null
            temperature.text = null
        }
    }
}