package com.example.weatherapp.core.domain.model

/**
 * Domain model for current weather data
 */
data class Weather(
    val location: Location,
    val temperature: Temperature,
    val condition: WeatherCondition,
    val wind: Wind,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int,
    val cloudiness: Int,
    val timestamp: Long,
    val sunrise: Long,
    val sunset: Long
)

data class Temperature(
    val current: Double,
    val feelsLike: Double,
    val min: Double,
    val max: Double
)

data class WeatherCondition(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) {
    fun getIconUrl(): String = "https://openweathermap.org/img/wn/$icon@2x.png"
}

data class Wind(
    val speed: Double,
    val degrees: Int
) {
    fun getDirection(): String {
        return when (degrees) {
            in 0..22 -> "N"
            in 23..67 -> "NE"
            in 68..112 -> "E"
            in 113..157 -> "SE"
            in 158..202 -> "S"
            in 203..247 -> "SW"
            in 248..292 -> "W"
            in 293..337 -> "NW"
            in 338..360 -> "N"
            else -> "N"
        }
    }
}
