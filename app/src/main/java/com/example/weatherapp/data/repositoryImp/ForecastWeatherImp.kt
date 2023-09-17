package com.example.weatherapp.data.remote.repositoryImp

import android.util.Log
import com.example.weatherapp.data.remote.ForecastWeatherApi
import com.example.weatherapp.domin.model.repository.WeatherResponse
import com.example.weatherapp.domin.modelForcstWether.ForecstWeatherResponse
import com.example.weatherapp.domin.repository.ForecastWeatherReository
import com.example.weatherapp.util.Resource

class ForecastWeatherImp(
    private val forecastWeatherApi: ForecastWeatherApi
) : ForecastWeatherReository {


    override suspend fun getWeatherForecastDays(lat: Double, long: Double): Resource<ForecstWeatherResponse> {
        return try {
            val response = forecastWeatherApi.getForecastWeatherApi(lat, long)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.d("loay5", "getWeatherForecastDays:${e.message}")
            Resource.Error(e.message)
        }
    }


}