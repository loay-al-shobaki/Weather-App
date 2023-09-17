package com.example.weatherapp.domin.model

import com.google.gson.annotations.SerializedName

data class WeatherCondition(
    @SerializedName("code") val code: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("description") val description: String
)