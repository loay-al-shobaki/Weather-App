package com.example.weatherapp.ui.theme.components

import android.util.Log
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon

import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

import com.example.weatherapp.R
import com.example.weatherapp.domin.model.TemperatureaPerDay
import com.example.weatherapp.domin.model.repository.TemperaturePerFourHoure
import com.example.weatherapp.util.WeatherScreenEvent

import com.example.weatherapp.util.WeatherState

@Composable
fun WeatherScreen(
    state: WeatherState,
    onEvent: (WeatherScreenEvent) -> Unit
) {

    var weatherContentVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
           ,
    ) {
        if (state.isLoading) {
            LoadingContent()
        } else {
            if (state.error != null) {
                ErrorContent(
                    error = state.error,
                    onRetry = {
                        onEvent(WeatherScreenEvent.onRetryButtonCliked)
                    }
                )
            } else {
                // Display WeatherContent only when weatherContentVisible is true
                if (weatherContentVisible) {
                    WeatherContent(state = state)
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        // Replace these with actual latitude and longitude values
        val lat = "12.34"
        val long = "56.78"

        // Trigger the event in the ViewModel to update the state
        onEvent(WeatherScreenEvent.onGetLatAndlong(lat,long))

        // Set weatherContentVisible to true when the event is triggered
        weatherContentVisible = true
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent), // Optional, add a background if needed
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Black
        )
    }
}

@Composable
fun ErrorContent(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "You must be connected to the Internet", color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun WeatherContent(state: WeatherState) {

    TopWeatherScreen(state.currentCity)
    CurrentWeather(
        imageUrl = state.imageCurrentTemperature,
        temperature = state.currentTemperature,
        maxTemperature = state.maxTemperature,
        minTemperature = state.minTemperature
    )
    WeatherTodayForecast(
        today = state.dateToday,
        state.listOfTemperatureAndTimeAndImgUrlPerfourHoures
    )
    WeatherForecastPerDay(state.listOfTemperatureAndDayNameAndImgUrlPerDays)
}


@Composable
fun TopWeatherScreen(
    city: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "location",
                tint = Color.White.copy(alpha = 0.95f)

            )
            Text(
                text = city,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "arrow_down",
                tint = Color.White.copy(alpha = 0.95f)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.outline_notifications_24),
            contentDescription = "location",
            tint = Color.White.copy(alpha = 0.95f),
            modifier = Modifier.size(26.dp)
        )

    }
}

@Composable
fun CurrentWeather(
    imageUrl: String,
    temperature: String,
    maxTemperature: String,
    minTemperature: String,
    state: WeatherState = WeatherState()
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        ImageHolder(imageUrl = imageUrl)
        Text(
            text = temperature,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 50.sp
        )
        Text(
            text = "Precipitations",
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        )
        Text(
            text = "Max.: $maxTemperature  Min.: $minTemperature",
            color = Color.White, fontSize = 17.sp
        )


    }

}

@Composable
fun WeatherTodayForecast(
    today: String,
    temperaturePerFourHoureList: List<TemperaturePerFourHoure>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onSecondaryContainer)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Today",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = today,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.W400
            )
        }

        LazyRow(contentPadding = PaddingValues(8.dp)) {
            items(temperaturePerFourHoureList) {
                ColumnWeatherOneHouresComponent(
                    temperature = it.temperature,
                    time = it.time,
                    img = it.imgUrl
                )
            }
        }
    }


}


@Composable
fun ColumnWeatherOneHouresComponent(temperature: String, img: String, time: String) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = temperature,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.heightIn(8.dp))
        ImageHolder(
            imageUrl = img,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.heightIn(4.dp))
        Text(text = time, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.W500)

    }
}


@Composable
fun WeatherForecastPerDay(temperaturePerFourHoureList: List<TemperatureaPerDay>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(28.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onSecondaryContainer)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Next Forecast",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_date),
                contentDescription = "date",
                tint = Color.White.copy(alpha = 0.95f),
                modifier = Modifier.size(26.dp)
            )
        }
        LazyColumn() {
            items(temperaturePerFourHoureList) {
                RowWeatherOneHouresComponent(
                    temperature = it.temperature,
                    dayName = it.dayName,
                    img = it.imgUrl
                )
            }

        }

    }
}

@Composable
fun RowWeatherOneHouresComponent(temperature: String, img: String, dayName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        ) {

        Text(
            text = dayName,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.heightIn(8.dp))

        ImageHolder(
            imageUrl = img,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.heightIn(4.dp))
        Text(
            text = temperature,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        )

    }
}


@Composable
fun ImageHolder(
    imageUrl: String,
    modifier: Modifier = Modifier
) {

    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .size(120.dp),
        placeholder = painterResource(id = R.drawable.ic_pinding),
        error = painterResource(id = R.drawable.ic_error)
    )
    Log.d("loay", "ImageHolder: $imageUrl")
}


@Preview(heightDp = 770, widthDp = 380, showBackground = true, backgroundColor = 0xff33aadd)
@Composable()
fun PreviewWeatherScreen() {
//    WeatherScreen()
}