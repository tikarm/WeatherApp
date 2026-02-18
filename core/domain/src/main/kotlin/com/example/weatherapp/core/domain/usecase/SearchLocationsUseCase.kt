package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.LocationRepository
import javax.inject.Inject

class SearchLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(query: String): Result<List<Location>> {
        if (query.isBlank() || query.length < 2) {
            return Result.Success(emptyList())
        }
        return locationRepository.searchLocations(query)
    }
}
