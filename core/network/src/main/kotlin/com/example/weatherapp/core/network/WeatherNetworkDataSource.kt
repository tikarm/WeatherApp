package com.example.weatherapp.core.network

import com.example.weatherapp.core.network.api.GeocodingApiService
import com.example.weatherapp.core.network.api.WeatherApiService
import com.example.weatherapp.core.network.model.ForecastResponse
import com.example.weatherapp.core.network.model.GeocodingResponse
import com.example.weatherapp.core.network.model.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherNetworkDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val geocodingApiService: GeocodingApiService,
    private val apiKey: String
) {

    suspend fun getCurrentWeatherByCoordinates(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {
        return weatherApiService.getCurrentWeatherByCoordinates(
            lat = latitude,
            lon = longitude,
            appId = apiKey
        )
    }

    suspend fun getCurrentWeatherByCity(city: String): WeatherResponse {
        return weatherApiService.getCurrentWeatherByCity(
            city = city,
            appId = apiKey
        )
    }

    suspend fun getForecastByCoordinates(
        latitude: Double,
        longitude: Double
    ): ForecastResponse {
        return weatherApiService.getForecastByCoordinates(
            lat = latitude,
            lon = longitude,
            appId = apiKey
        )
    }

    suspend fun getForecastByCity(city: String): ForecastResponse {
        return weatherApiService.getForecastByCity(
            city = city,
            appId = apiKey
        )
    }
    
    suspend fun searchLocations(query: String, limit: Int = 5): List<GeocodingResponse> {
        return geocodingApiService.searchLocationsByName(
            city = query,
            limit = limit,
            appId = apiKey
        )
    }
    
    suspend fun getLocationByCoordinates(
        latitude: Double,
        longitude: Double
    ): List<GeocodingResponse> {
        return geocodingApiService.getLocationByCoordinates(
            lat = latitude,
            lon = longitude,
            appId = apiKey
        )
    }
}
