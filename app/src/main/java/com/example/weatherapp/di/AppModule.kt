package com.example.weatherapp.di

import com.example.weatherapp.data.remote.ForecastWeatherApi
import com.example.weatherapp.data.remote.ForecastWeatherApi.Companion.BASE_URL_FORCAST
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherApi.Companion.BASE_URL
import com.example.weatherapp.data.remote.repositoryImp.ForecastWeatherImp
import com.example.weatherapp.data.remote.repositoryImp.WeatherRepositoryImp
import com.example.weatherapp.domin.model.repository.WeatherRepository
import com.example.weatherapp.domin.repository.ForecastWeatherReository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRep(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideForecastApi(): ForecastWeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_FORCAST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ForecastWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideForecastWeather(forecastWeatherApi: ForecastWeatherApi): ForecastWeatherReository {
        return ForecastWeatherImp(forecastWeatherApi)
    }


}