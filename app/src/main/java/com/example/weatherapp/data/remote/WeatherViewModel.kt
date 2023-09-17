package com.example.weatherapp.data.remote

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domin.model.repository.WeatherRepository
import com.example.weatherapp.domin.repository.ForecastWeatherReository
import com.example.weatherapp.localData.localData.converForecsttWeatherResponseToListTemperaturePerDays
import com.example.weatherapp.localData.localData.convertWeatherResponseToListTemperaturePerFourHoure
import com.example.weatherapp.localData.localData.convertTimestampToDateTodayAndMonth
import com.example.weatherapp.util.Resource
import com.example.weatherapp.util.WeatherScreenEvent
import com.example.weatherapp.util.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val forcastRepository: ForecastWeatherReository
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
    //  var weather by mutableStateOf(WeatherResponse())


    fun OnEvent(event: WeatherScreenEvent) {
        when (event) {
            is WeatherScreenEvent.onRetryButtonCliked ->{
                getCurrentWeathre(31.4913, 34.461044)
                getForecastFourHoures(31.4913, 34.461044)
                getForecstWeatherFor16Days(31.4913, 34.461044)
            }
            is WeatherScreenEvent.onGetLatAndlong ->{
                getCurrentWeathre(event.lat.toDouble(),event.long.toDouble())
                getForecastFourHoures(event.lat.toDouble(),event.long.toDouble())
                getForecstWeatherFor16Days(event.lat.toDouble(),event.long.toDouble())
            }
        }
    }


    private fun getCurrentWeathre(lot: Double, long: Double) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val weatherRepository = weatherRepository.getCurrentWeather(lot, long)

            when (weatherRepository) {
                is Resource.Success -> {
                    val result = weatherRepository.data!!
                    state = state.copy(
                        currentTemperature = "${result.main.temp}°",
                        isLoading = false,
                        error = null,
                        currentCity = result.name,
                        imageCurrentTemperature = "http://openweathermap.org/img/wn/${result.weather[0].icon}@2x.png",
                        maxTemperature = "${result.main.tempMax.toInt()}°",
                        minTemperature = "${result.main.tempMin.toInt()}°",
                        dateToday = convertTimestampToDateTodayAndMonth(result.dt.toLong())
                    )
                    Log.d(
                        "lo",
                        "getCurrentWeathre: https://openweathermap.org/img/wn/${result.weather[0].icon}@2x.png"
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = weatherRepository.message,

                        )

                    Log.d("loay", "getCurrentWeathre: ${weatherRepository.message}")
                }

                else -> {}
            }

        }
    }

    private fun getForecastFourHoures(lot: Double, long: Double) {
        viewModelScope.launch {
            val weatherResource = weatherRepository.getForecastFourHoures(lot, long)
            val result = weatherResource.data?.let { result ->
                when (weatherResource) {
                    is Resource.Success -> {
                        state = state.copy(
                            listOfTemperatureAndTimeAndImgUrlPerfourHoures =
                            convertWeatherResponseToListTemperaturePerFourHoure(result)
                        )

                    }

                    is Resource.Error -> {
                        state = state.copy(error = weatherResource.message)

                    }

                    else -> {}
                }
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getForecstWeatherFor16Days(lot: Double, long: Double) {
        viewModelScope.launch {
            val weatherRespons = forcastRepository.getWeatherForecastDays(lot, long)
            when (weatherRespons) {
                is Resource.Success -> {
                    state = state.copy(
                        listOfTemperatureAndDayNameAndImgUrlPerDays =
                        converForecsttWeatherResponseToListTemperaturePerDays(
                            weatherRespons.data!!
                        )
                    )
                    Log.d(
                        "loay5", "getForecstWeatherFor16Days:${
                            converForecsttWeatherResponseToListTemperaturePerDays(
                                weatherRespons.data!!
                            )
                        } "
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = weatherRespons.message,
                        listOfTemperatureAndTimeAndImgUrlPerfourHoures = emptyList()

                    )
                    Log.d("loay5", "getForecstWeatherFor16Days:${weatherRespons.message} ")

                }

                else -> {}
            }


        }
    }


}