package com.example.android.bringyouumbrella.weatherMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.bringyouumbrella.R
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        viewModel.currentWeather.observe(this, Observer { weathers ->
            mainWeather.text = weathers[0].main
        })
        viewModel.getWeather()

        return view
    }
}