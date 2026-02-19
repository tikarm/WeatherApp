package com.example.weatherapp.core.network.api

import com.example.weatherapp.core.network.model.ForecastResponse
import com.example.weatherapp.core.network.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * OpenWeatherMap API service interface
 * API Documentation: https://openweathermap.org/api
 */
interface WeatherApiService {
    
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String
    ): WeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String
    ): ForecastResponse
}
