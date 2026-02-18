package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Forecast
import com.example.weatherapp.core.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastByCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<Forecast> {
        if (cityName.isBlank()) {
            return Result.Error(IllegalArgumentException("City name cannot be empty"))
        }
        return weatherRepository.getForecastByCity(cityName)
    }
}
