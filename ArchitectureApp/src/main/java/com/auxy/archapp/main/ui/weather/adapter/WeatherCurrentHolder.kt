package com.auxy.archapp.main.ui.weather.adapter

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.auxy.archapp.databinding.ItemWeatherCurrentBinding

class WeatherCurrentHolder : EpoxyHolder() {
    lateinit var viewBinding: ItemWeatherCurrentBinding
    override fun bindView(itemView: View) {
        viewBinding = ItemWeatherCurrentBinding.bind(itemView)
    }
}
