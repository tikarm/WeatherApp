package com.example.weatherapp.core.domain.model

data class Location(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val state: String? = null
) {
    fun getDisplayName(): String {
        return if (state != null) {
            "$name, $state, $country"
        } else {
            "$name, $country"
        }
    }
}
