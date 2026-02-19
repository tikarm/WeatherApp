package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.domain.model.Location

interface LocationRepository {

    suspend fun getCurrentLocation(): Result<Location>

    suspend fun searchLocations(query: String): Result<List<Location>>
}
