package com.example.weatherapp.feature.weather.presentation

import androidx.compose.runtime.Immutable
import com.example.weatherapp.core.domain.model.DailyForecast
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.model.Weather

@Immutable
data class WeatherUiState(
    val currentWeather: Weather? = null,
    val weeklyForecast: List<DailyForecast> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val permissionDenied: Boolean = false
)

@Immutable
data class SearchUiState(
    val query: String = "",
    val searchResults: List<Location> = emptyList(),
    val isSearching: Boolean = false,
    val error: String? = null
)
