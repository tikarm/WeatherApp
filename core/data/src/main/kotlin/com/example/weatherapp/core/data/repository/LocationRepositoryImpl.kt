package com.example.weatherapp.core.data.repository

import com.example.weatherapp.core.common.util.Result
import com.example.weatherapp.core.data.mapper.toDomain
import com.example.weatherapp.core.data.source.LocationService
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.LocationRepository
import com.example.weatherapp.core.network.WeatherNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepositoryImpl @Inject constructor(
    private val locationService: LocationService,
    private val networkDataSource: WeatherNetworkDataSource
) : LocationRepository {
    
    override suspend fun getCurrentLocation(): Result<Location> {
        return try {
            val deviceLocation = locationService.getCurrentLocation()
            
            val geocodingResponse = networkDataSource.getLocationByCoordinates(
                latitude = deviceLocation.latitude,
                longitude = deviceLocation.longitude
            )
            
            if (geocodingResponse.isEmpty()) {
                return Result.Error(Exception("Unable to determine location name"))
            }
            
            Result.Success(geocodingResponse.first().toDomain())
        } catch (e: SecurityException) {
            Result.Error(Exception("Location permission denied"))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    
    override suspend fun searchLocations(query: String): Result<List<Location>> {
        return try {
            val response = networkDataSource.searchLocations(query)
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
