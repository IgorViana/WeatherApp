package com.example.android.bringyouumbrella.di.dagger2.module

import com.example.android.bringyouumbrella.repository.WeatherRepository
import com.example.android.bringyouumbrella.webService.RetrofitFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherModule {

    /*@Singleton
    @Provides
    fun provideRetrofitFactory(): RetrofitFactory {
        return RetrofitFactory()
    }

    @Provides
    fun provideWeatherRepository(retrofitFactory: RetrofitFactory): WeatherRepository {
        return WeatherRepository()
    }*/
}