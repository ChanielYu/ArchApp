package com.auxy.archapp.main.ui.weather.adapter

import android.widget.TextView
import butterknife.BindView
import com.auxy.archapp.R
import com.auxy.archapp.epoxy.ButterKnifeHolder

class WeatherCurrentHolder : ButterKnifeHolder() {
    @BindView(R.id.current_summary)
    lateinit var summary: TextView
    @BindView(R.id.date_time)
    lateinit var dateTime: TextView
    @BindView(R.id.temperature)
    lateinit var temperature: TextView
}