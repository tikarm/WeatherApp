package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.model.Temperature
import com.example.weatherapp.core.domain.model.Weather
import com.example.weatherapp.core.domain.model.WeatherCondition
import com.example.weatherapp.core.domain.model.Wind
import com.example.weatherapp.core.network.model.WeatherResponse

fun WeatherResponse.toDomain(): Weather {
    return Weather(
        location = Location(
            name = cityName,
            country = sys.country,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude,
            state = null
        ),
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
        wind = Wind(
            speed = wind.speed,
            degrees = wind.degrees
        ),
        humidity = main.humidity,
        pressure = main.pressure,
        visibility = visibility,
        cloudiness = clouds.cloudiness,
        timestamp = timestamp,
        sunrise = sys.sunrise,
        sunset = sys.sunset
    )
}
