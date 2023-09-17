package com.example.weatherapp.domin.model.repository

import com.example.weatherapp.util.Resource

interface WeatherRepository {

    suspend fun getCurrentWeather(
        lat: Double,
        long: Double
    ): Resource<WeatherResponse>

    suspend fun getForecastFourHoures(
        lat: Double,
        long: Double
    ): Resource<WeatherResponse>


}