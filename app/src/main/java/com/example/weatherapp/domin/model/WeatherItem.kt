package com.example.weatherapp.domin.model.repository

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: MainWeatherInfo,
    @SerializedName("weather")
    val weather: List<WeatherDescription>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("dt_txt")
    val dt_txt: String
)


