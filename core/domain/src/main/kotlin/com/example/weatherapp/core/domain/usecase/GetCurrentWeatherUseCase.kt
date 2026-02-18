package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Weather
import com.example.weatherapp.core.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<Weather> {
        return weatherRepository.getCurrentWeather(latitude, longitude)
    }
}
