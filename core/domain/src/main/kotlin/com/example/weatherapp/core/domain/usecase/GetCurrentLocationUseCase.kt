package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.LocationRepository
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Result<Location> {
        return locationRepository.getCurrentLocation()
    }
}
