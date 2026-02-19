package com.example.weatherapp.feature.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.usecase.GetCurrentLocationUseCase
import com.example.weatherapp.core.domain.usecase.GetCurrentWeatherUseCase
import com.example.weatherapp.core.domain.usecase.GetWeeklyForecastUseCase
import com.example.weatherapp.core.domain.usecase.SearchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
    private val searchLocationsUseCase: SearchLocationsUseCase
) : ViewModel() {

    private val _weatherUiState = MutableStateFlow(WeatherUiState())
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    init {
        setupSearchDebounce()
    }

    @OptIn(FlowPreview::class)
    private fun setupSearchDebounce() {
        viewModelScope.launch {
            searchUiState
                .map { it.query }
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.length < 2) {
                        _searchUiState.update { it.copy(searchResults = emptyList()) }
                    } else {
                        performSearch(query)
                    }
                }
        }
    }

    fun loadCurrentLocationWeather() {
        viewModelScope.launch {
            _weatherUiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    permissionDenied = false
                )
            }

            when (val locationResult = getCurrentLocationUseCase()) {
                is Result.Success -> {
                    val location = locationResult.data
                    loadWeatherForLocation(location.latitude, location.longitude)
                }

                is Result.Error -> {
                    val errorMessage = locationResult.exception.message
                    if (errorMessage?.contains("permission", ignoreCase = true) == true) {
                        _weatherUiState.update {
                            it.copy(isLoading = false, permissionDenied = true)
                        }
                    } else {
                        _weatherUiState.update {
                            it.copy(
                                isLoading = false,
                                error = "Unable to get location: ${locationResult.exception.message}"
                            )
                        }
                    }
                }

                is Result.Loading -> {}
            }
        }
    }

    private fun loadWeatherForLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            when (val weatherResult = getCurrentWeatherUseCase(latitude, longitude)) {
                is Result.Success -> {
                    _weatherUiState.update {
                        it.copy(
                            currentWeather = weatherResult.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    loadForecast(latitude, longitude)
                }

                is Result.Error -> {
                    _weatherUiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Unable to load weather: ${weatherResult.exception.message}"
                        )
                    }
                }

                is Result.Loading -> {}
            }
        }
    }

    private fun loadForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            when (val forecastResult = getWeeklyForecastUseCase(latitude, longitude)) {
                is Result.Success -> {
                    _weatherUiState.update {
                        it.copy(weeklyForecast = forecastResult.data)
                    }
                }

                is Result.Error -> {}

                is Result.Loading -> {}
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchUiState.update { it.copy(query = query) }
    }

    private suspend fun performSearch(query: String) {
        _searchUiState.update { it.copy(isSearching = true, error = null) }

        when (val result = searchLocationsUseCase(query)) {
            is Result.Success -> {
                _searchUiState.update {
                    it.copy(
                        searchResults = result.data,
                        isSearching = false,
                        error = null
                    )
                }
            }

            is Result.Error -> {
                _searchUiState.update {
                    it.copy(
                        searchResults = emptyList(),
                        isSearching = false,
                        error = "Search failed: ${result.exception.message}"
                    )
                }
            }

            is Result.Loading -> {}
        }
    }

    fun onLocationSelected(location: Location) {
        loadWeatherForLocation(location.latitude, location.longitude)
        clearSearch()
    }

    fun clearSearch() {
        _searchUiState.update { SearchUiState() }
    }

    fun onPermissionDenied() {
        _weatherUiState.update {
            it.copy(isLoading = false, permissionDenied = true)
        }
    }

    fun resetPermissionDenied() {
        _weatherUiState.update {
            it.copy(permissionDenied = false)
        }
    }

    fun retry() {
        loadCurrentLocationWeather()
    }
}
