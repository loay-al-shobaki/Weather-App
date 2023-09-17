package com.example.weatherapp.data.remote

import com.example.weatherapp.domin.model.repository.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val API_ID = "9b7cccf4e8d5f680212282f27df70fba"
        const val APIID = "appid"
        const val LAT = "lat"
        const val LONG = "lon"
        const val UNITS = "metric"
    }


    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query(LAT) lat: Double,
        @Query(LONG) lon: Double,
        @Query(APIID) apiKey: String = API_ID,
        @Query("units") units: String = UNITS,
        ): WeatherResponse


    @GET("data/2.5/forecast")
    suspend fun getForecastFiveDayAndThereHoures(
        @Query(LAT) Lat: Double,
        @Query(LONG) long: Double,
        @Query(APIID) api_Id: String = API_ID,
        @Query("units") units: String = UNITS,
    ): WeatherResponse
}