package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Forecast
import com.example.weatherapp.core.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Result<Weather>

    suspend fun getCurrentWeatherByCity(
        cityName: String
    ): Result<Weather>

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Result<Forecast>

    suspend fun getForecastByCity(
        cityName: String
    ): Result<Forecast>
}
