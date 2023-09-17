package com.example.weatherapp.domin.model.repository

import com.google.gson.annotations.SerializedName

data class WeatherDescription(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)
