package com.auxy.archapp.main.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.airbnb.epoxy.EpoxyRecyclerView
import com.auxy.archapp.R
import com.auxy.archapp.main.ui.weather.adapter.weatherCurrent
import com.auxy.archapp.utils.TemperatureUtil
import com.auxy.archapp.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import javax.inject.Inject

class WeatherFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.text_weather)
    internal lateinit var textView: TextView

    @BindView(R.id.swipe_refresh)
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @BindView(R.id.recycler_view)
    internal lateinit var recyclerView: EpoxyRecyclerView

    private lateinit var weatherViewModel: WeatherViewModel

    private var unBinder: Unbinder? = null

    private val dateFormat: SimpleDateFormat = (SimpleDateFormat.getDateTimeInstance() as SimpleDateFormat).apply {
        applyPattern("yyyy-MM-dd HH:mm:ss")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_weather, container, false)
        unBinder = ButterKnife.bind(this, root)
        swipeRefreshLayout.setOnRefreshListener(this)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBinder?.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory)[WeatherViewModel::class.java]
        weatherViewModel.text.observe(this, Observer {
            textView.text = it
        })
        weatherViewModel.isLoading.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it == true
        })
        weatherViewModel.weather.observe(this, Observer { weather ->
            recyclerView.withModels {
                weatherCurrent {
                    id("Current Item")
                    summaryText(weather.currently?.summary)
                    timeText(weather.currently?.time?.let {
                        dateFormat.format(it)
                    })
                    temperatureText(weather.currently?.temperature?.let {
                        "${TemperatureUtil.w2c(it).toInt()}°"
                    })
                }
                weather.hourly?.data?.forEach { dataX ->
                    dataX?.time?.let { date ->
                        weatherCurrent {
                            id("${date.day}-${date.hours}")
                            summaryText(dataX.summary)
                            timeText(dateFormat.format(date))
                            temperatureText(dataX.temperature?.let {
                                "${TemperatureUtil.w2c(it).toInt()}°"
                            })
                        }
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        weatherViewModel.refreshWeather()
    }
}
