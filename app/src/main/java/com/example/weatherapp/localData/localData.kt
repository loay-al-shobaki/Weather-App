package com.example.weatherapp.localData

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.weatherapp.domin.model.TemperatureaPerDay
import com.example.weatherapp.domin.model.repository.WeatherResponse
import com.example.weatherapp.domin.model.repository.TemperaturePerFourHoure
import com.example.weatherapp.domin.modelForcstWether.ForecstWeatherResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

object localData {

    fun convertTimestampToDateTodayAndMonth(timestamp: Long): String {
        // Your Unix timestamp
        val date = Date(timestamp * 1000) // Convert to milliseconds

        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val hourFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val monthName = monthFormat.format(date)
        val dayNumber = dayFormat.format(date)
        val hour = hourFormat.format(date)

        return "$monthName, $dayNumber"
    }

    fun convertTimestampTohour(timestamp: Long): String {
        // Your Unix timestamp
        val date = Date(timestamp * 1000) // Convert to milliseconds
        val hourFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val hour = hourFormat.format(date)

        return hour
    }

    fun convertWeatherResponseToListTemperaturePerFourHoure(weatherResponse: WeatherResponse): List<TemperaturePerFourHoure> {
        return weatherResponse.list.take(7).map { forecast ->
            val temperature = forecast.main.temp.toInt().toString() + "°C"
            val time = convertTo12HourFormat(forecast.dt_txt.split(" ")[1].substring(0, 5))
            val icon = getImageTemperaturePerFourHoure(forecast.weather[0].icon)
            Log.d("loay22", "convertWeatherResponseToListTemperaturePerFourHoure: ${getImageTemperaturePerFourHoure(forecast.weather[0].icon)}")
            TemperaturePerFourHoure(temperature, time, icon)
        }
    }




    fun converForecsttWeatherResponseToListTemperaturePerDays(forecstWeatherResponse: ForecstWeatherResponse): List<TemperatureaPerDay> {
        return forecstWeatherResponse.data.take(7).map { forecast ->
            val temp = forecast.temp.toString() + "°C"
            Log.d("loay2", "converForecsttWeatherResponseToListTemperaturePerDays: ${forecast.datetime}")
            val dayName = forecast.datetime
            val icon = getImageTemperaturePerdays(forecast.weather.icon)


            TemperatureaPerDay(temp, dayName, icon)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayOfWeekFromDate(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)

        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

        return dayOfWeek
    }
    fun getImageTemperaturePerFourHoure(Codeurl: String): String {
        return "http://openweathermap.org/img/wn/$Codeurl@2x.png"
    }

    fun getImageTemperaturePerdays(Codeurl: String): String {
        return "http://cdn.weatherbit.io/static/img/icons/$Codeurl.png"
    }

    fun convertTo12HourFormat(time24Hour: String): String {
        val parts = time24Hour.split(":")
        val hour = parts[0].toInt()
        val minute = parts[1]

        val amPm = if (hour < 12) "AM" else "PM"
        val hour12 = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour

        return "$hour12:$minute $amPm"
    }

}

