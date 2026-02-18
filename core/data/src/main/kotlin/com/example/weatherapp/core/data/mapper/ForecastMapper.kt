package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain.model.DailyForecast
import com.example.weatherapp.core.domain.model.Forecast
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.model.Temperature
import com.example.weatherapp.core.domain.model.WeatherCondition
import com.example.weatherapp.core.domain.model.Wind
import com.example.weatherapp.core.network.model.ForecastItem
import com.example.weatherapp.core.network.model.ForecastResponse
import java.util.Calendar

/**
 * Map network ForecastResponse to domain Forecast model
 * Groups 3-hour intervals into daily forecasts
 */
fun ForecastResponse.toDomain(): Forecast {
    val location = Location(
        name = city.name,
        country = city.country,
        latitude = city.coordinates.latitude,
        longitude = city.coordinates.longitude,
        state = null
    )
    
    // Group forecast items by day and take one per day (around noon)
    val dailyForecasts = list
        .groupBy { item ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = item.timestamp * 1000
            }
            // Group by year, month, day
            Triple(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        .map { (_, items) ->
            // Take the midday forecast (closest to 12:00)
            items.minByOrNull { item ->
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = item.timestamp * 1000
                }
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                kotlin.math.abs(hour - 12)
            } ?: items.first()
        }
        .map { it.toDailyForecast() }
    
    return Forecast(
        location = location,
        dailyForecasts = dailyForecasts
    )
}

private fun ForecastItem.toDailyForecast(): DailyForecast {
    return DailyForecast(
        date = timestamp,
        temperature = Temperature(
            current = main.temperature,
            feelsLike = main.feelsLike,
            min = main.tempMin,
            max = main.tempMax
        ),
        condition = WeatherCondition(
            id = weather.first().id,
            main = weather.first().main,
            description = weather.first().description,
            icon = weather.first().icon
        ),
        humidity = main.humidity,
        wind = Wind(
            speed = wind.speed,
            degrees = wind.degrees
        ),
        probabilityOfPrecipitation = probabilityOfPrecipitation
    )
}
