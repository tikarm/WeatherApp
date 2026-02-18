package com.example.weatherapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    @SerialName("name")
    val name: String,
    @SerialName("local_names")
    val localNames: Map<String, String>? = null,
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    @SerialName("country")
    val country: String,
    @SerialName("state")
    val state: String? = null
)
