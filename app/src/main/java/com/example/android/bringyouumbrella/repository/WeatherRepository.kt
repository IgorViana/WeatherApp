package com.example.android.bringyouumbrella.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.bringyouumbrella.model.Weather
import com.example.android.bringyouumbrella.webService.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherRepository() {

    val currentWeather: MutableLiveData<List<Weather>> by lazy {
        MutableLiveData<List<Weather>>()
    }

    private val compositeDisposable = CompositeDisposable()

    fun getWeather() {
        val observable = RetrofitFactory().weatherService().getCityWeather("London")

        val disposable = observable
            .doOnNext { }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    currentWeather.postValue(result.weather)
                },
                { error ->
                    error.printStackTrace()
                },
                {

                }
            )
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.clear()
    }

}