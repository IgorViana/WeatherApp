package com.example.android.bringyouumbrella.repository

import androidx.lifecycle.MutableLiveData
import com.example.android.bringyouumbrella.model.WeatherResponse
import com.example.android.bringyouumbrella.webService.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherRepository() {

    val currentWeather: MutableLiveData<WeatherResponse> by lazy {
        MutableLiveData<WeatherResponse>()
    }

    private val compositeDisposable = CompositeDisposable()

    fun dispose() {
        compositeDisposable.clear()
    }

    fun getWeather(latitude: Double, longitude:Double) {
        val observable = RetrofitFactory().weatherService().getCityWeather(latitude= latitude, longitude = longitude)

        val disposable = observable
            .doOnNext { }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    currentWeather.postValue(result!!)
                },
                { error ->
                    error.printStackTrace()
                },
                {

                }
            )
        compositeDisposable.add(disposable)
    }

}