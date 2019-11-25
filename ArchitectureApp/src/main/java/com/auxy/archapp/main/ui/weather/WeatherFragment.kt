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
import com.auxy.archapp.R
import com.auxy.archapp.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WeatherFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.text_weather)
    internal lateinit var textView: TextView

    @BindView(R.id.swipe_refresh)
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var weatherViewModel: WeatherViewModel

    private var unBinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_weather, container, false)
        unBinder = ButterKnife.bind(this, root)
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
        weatherViewModel.weather.observe(this, Observer {weather->
            weather.hourly?.data?.size
        })
    }
}
