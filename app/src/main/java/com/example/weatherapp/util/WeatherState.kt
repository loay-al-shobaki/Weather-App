package com.example.weatherapp.util

import com.example.weatherapp.domin.model.TemperatureaPerDay
import com.example.weatherapp.domin.model.repository.TemperaturePerFourHoure

data class WeatherState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentTemperature: String = "",
    val imageCurrentTemperature: String = "",
    val currentCity: String = "",
    val maxTemperature: String = "",
    val minTemperature: String = "",
    val dateToday: String = "",
    val listOfTemperatureAndTimeAndImgUrlPerfourHoures: List<TemperaturePerFourHoure> = emptyList(),
    val listOfTemperatureAndDayNameAndImgUrlPerDays: List<TemperatureaPerDay> = emptyList(),

    )
