package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.data.mapper.toDomain
import com.example.weatherapp.core.domain.model.Forecast
import com.example.weatherapp.core.domain.model.Weather
import com.example.weatherapp.core.domain.repository.WeatherRepository
import com.example.weatherapp.core.network.WeatherNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val networkDataSource: WeatherNetworkDataSource
) : WeatherRepository {
    
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Result<Weather> {
        return try {
            val response = networkDataSource.getCurrentWeatherByCoordinates(latitude, longitude)
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Result<Forecast> {
        return try {
            val response = networkDataSource.getForecastByCoordinates(latitude, longitude)
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
