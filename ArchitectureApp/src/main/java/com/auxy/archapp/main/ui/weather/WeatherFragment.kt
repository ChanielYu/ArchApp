package com.auxy.archapp.main.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.auxy.archapp.databinding.FragmentWeatherBinding
import com.auxy.archapp.main.ui.weather.adapter.weatherCurrent
import com.auxy.archapp.utils.TemperatureUtil
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val dateFormat: SimpleDateFormat =
        (SimpleDateFormat.getDateTimeInstance() as SimpleDateFormat).apply {
            applyPattern("yyyy-MM-dd HH:mm:ss")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.text.observe(viewLifecycleOwner, {
            binding.textWeather.text = it
        })
        weatherViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it == true
        })
        weatherViewModel.weather.observe(viewLifecycleOwner, { weather ->
            binding.recyclerView.withModels {
                weatherCurrent {
                    id("com.auxy.archapp.main.ui.notifications.model.Current Item")
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentWeatherBinding.inflate(inflater, container, false).apply {
            _binding = this
            swipeRefresh.setOnRefreshListener {
                weatherViewModel.refreshWeather()
            }
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
