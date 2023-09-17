package com.example.weatherapp.data.remote

import com.example.weatherapp.domin.model.repository.WeatherResponse
import com.example.weatherapp.domin.modelForcstWether.ForecstWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastWeatherApi {
    companion object {
        const val BASE_URL_FORCAST = "https://api.weatherbit.io"
        const val KEY = "68c2864c281c4834816e575df4594211"
    }

    @GET("v2.0/forecast/daily")
    suspend fun getForecastWeatherApi(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("key") key: String = KEY
    ): ForecstWeatherResponse
}