package com.example.weatherapp.core.network.api

import com.example.weatherapp.core.network.model.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * OpenWeatherMap Geocoding API service interface
 * API Documentation: https://openweathermap.org/api/geocoding-api
 */
interface GeocodingApiService {
    
    @GET("geo/1.0/direct")
    suspend fun searchLocationsByName(
        @Query("q") city: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") appId: String
    ): List<GeocodingResponse>
    
    @GET("geo/1.0/reverse")
    suspend fun getLocationByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 1,
        @Query("appid") appId: String
    ): List<GeocodingResponse>
}
