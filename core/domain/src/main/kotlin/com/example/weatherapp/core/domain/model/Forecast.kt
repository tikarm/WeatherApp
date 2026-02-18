package com.example.weatherapp.core.domain.model

data class Forecast(
    val location: Location,
    val dailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    val date: Long,
    val temperature: Temperature,
    val condition: WeatherCondition,
    val humidity: Int,
    val wind: Wind,
    val probabilityOfPrecipitation: Double
)

fun Forecast.getNextDays(days: Int): List<DailyForecast> {
    return dailyForecasts.take(days)
}
