package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.DailyForecast
import com.example.weatherapp.core.domain.model.getNextDays
import com.example.weatherapp.core.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeeklyForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<List<DailyForecast>> {
        return when (val result = weatherRepository.getForecast(latitude, longitude)) {
            is Result.Success -> {
                val forecast = result.data.getNextDays(7)
                Result.Success(forecast)
            }
            is Result.Error -> Result.Error(result.exception)
            is Result.Loading -> Result.Loading
        }
    }
}
