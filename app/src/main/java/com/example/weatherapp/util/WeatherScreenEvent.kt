package com.example.weatherapp.util

sealed class WeatherScreenEvent {
    object onRetryButtonCliked : WeatherScreenEvent()
    data class onGetLatAndlong(var lat: String, val long: String) : WeatherScreenEvent()
}
