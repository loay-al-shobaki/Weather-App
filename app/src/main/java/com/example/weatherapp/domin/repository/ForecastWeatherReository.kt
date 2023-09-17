package com.example.weatherapp.domin.repository

import com.example.weatherapp.domin.model.repository.WeatherResponse
import com.example.weatherapp.domin.modelForcstWether.ForecstWeatherResponse
import com.example.weatherapp.util.Resource

interface ForecastWeatherReository {

   suspend fun getWeatherForecastDays(
        lot: Double,
        long: Double
    ):Resource<ForecstWeatherResponse>
}