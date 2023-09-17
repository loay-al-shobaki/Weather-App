package com.example.weatherapp.domin.model.repository

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val `3h`: Double
)