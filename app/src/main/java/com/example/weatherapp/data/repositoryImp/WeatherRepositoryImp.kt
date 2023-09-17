package com.example.weatherapp.data.remote.repositoryImp

import android.util.Log
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domin.model.repository.WeatherRepository
import com.example.weatherapp.domin.model.repository.WeatherResponse
import com.example.weatherapp.util.Resource

class WeatherRepositoryImp(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, long: Double): Resource<WeatherResponse> {
        return try {
            val response = weatherApi.getCurrentWeather(lat, long)
            Log.d("loay", "getCurrentWeather: ${response}")
            Resource.Success(response)
        } catch (e: Exception) {
            Log.d("loay", "getCurrentWeather: ${e.message}")
            Resource.Error(message = e.message)
        }
    }

    override suspend fun getForecastFourHoures(
        lat: Double,
        long: Double
    ): Resource<WeatherResponse> {
        return try {
            val response = weatherApi.getForecastFiveDayAndThereHoures(lat, long)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message)

        }
    }

}