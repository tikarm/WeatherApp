package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.network.model.GeocodingResponse

fun GeocodingResponse.toDomain(): Location {
    return Location(
        name = name,
        country = country,
        latitude = latitude,
        longitude = longitude,
        state = state
    )
}

fun List<GeocodingResponse>.toDomain(): List<Location> {
    return map { it.toDomain() }
}
