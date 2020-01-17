package com.example.android.bringyouumbrella.di.dagger2.component

import com.example.android.bringyouumbrella.di.dagger2.module.WeatherModule
import com.example.android.bringyouumbrella.repository.WeatherRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherModule::class])
interface ApplicationComponent {

    fun inject(weatherRepository: WeatherRepository)
}