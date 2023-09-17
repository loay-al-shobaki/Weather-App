package com.example.weatherapp.domin.modelForcstWether


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("app_max_temp")
    val appMaxTemp: Double = 0.0,
    @SerializedName("app_min_temp")
    val appMinTemp: Double = 0.0,
    @SerializedName("clouds")
    val clouds: Int = 0,
    @SerializedName("clouds_hi")
    val cloudsHi: Int = 0,
    @SerializedName("clouds_low")
    val cloudsLow: Int = 0,
    @SerializedName("clouds_mid")
    val cloudsMid: Int = 0,
    @SerializedName("datetime")
    val datetime: String = "",
    @SerializedName("dewpt")
    val dewpt: Double = 0.0,
    @SerializedName("high_temp")
    val highTemp: Double = 0.0,
    @SerializedName("low_temp")
    val lowTemp: Double = 0.0,
    @SerializedName("max_dhi")
    val maxDhi: Any = Any(),
    @SerializedName("max_temp")
    val maxTemp: Double = 0.0,
    @SerializedName("min_temp")
    val minTemp: Double = 0.0,
    @SerializedName("moon_phase")
    val moonPhase: Double = 0.0,
    @SerializedName("moon_phase_lunation")
    val moonPhaseLunation: Double = 0.0,
    @SerializedName("moonrise_ts")
    val moonriseTs: Int = 0,
    @SerializedName("moonset_ts")
    val moonsetTs: Int = 0,
    @SerializedName("ozone")
    val ozone: Double = 0.0,
    @SerializedName("pop")
    val pop: Int = 0,
    @SerializedName("precip")
    val precip: Double = 0.0,
    @SerializedName("pres")
    val pres: Double = 0.0,
    @SerializedName("rh")
    val rh: Int = 0,
    @SerializedName("slp")
    val slp: Double = 0.0,
    @SerializedName("snow")
    val snow: Int = 0,
    @SerializedName("snow_depth")
    val snowDepth: Int = 0,
    @SerializedName("sunrise_ts")
    val sunriseTs: Int = 0,
    @SerializedName("sunset_ts")
    val sunsetTs: Int = 0,
    @SerializedName("temp")
    val temp: Double = 0.0,
    @SerializedName("ts")
    val ts: Int = 0,
    @SerializedName("uv")
    val uv: Double = 0.0,
    @SerializedName("valid_date")
    val validDate: String = "",
    @SerializedName("vis")
    val vis: Double = 0.0,
    @SerializedName("weather")
    val weather: Weather = Weather(),
    @SerializedName("wind_cdir")
    val windCdir: String = "",
    @SerializedName("wind_cdir_full")
    val windCdirFull: String = "",
    @SerializedName("wind_dir")
    val windDir: Int = 0,
    @SerializedName("wind_gust_spd")
    val windGustSpd: Double = 0.0,
    @SerializedName("wind_spd")
    val windSpd: Double = 0.0
)