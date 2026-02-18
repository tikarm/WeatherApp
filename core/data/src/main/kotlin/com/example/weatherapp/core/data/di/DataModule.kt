package com.example.weatherapp.core.data.di

import com.example.weatherapp.core.data.repository.LocationRepositoryImpl
import com.example.weatherapp.core.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.core.domain.repository.LocationRepository
import com.example.weatherapp.core.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
    
    @Binds
    @Singleton
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationRepositoryImpl
    ): LocationRepository
}
