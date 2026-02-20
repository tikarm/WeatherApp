# Weather App

An Android weather forecast application built with modern Android development practices.

## Features

- ✅ Display current day weather forecast
- ✅ Display weekly (7-day) weather forecast
- ✅ Automatic location detection for current city
- ✅ Search and view weather for any city
- ✅ Clean, modern UI with Jetpack Compose

## Tech Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Async Programming**: Coroutines & Flow
- **Architecture**: Clean Architecture (3 layers)
- **Dependency Injection**: Hilt

### Key Libraries
- **Networking**: Retrofit + OkHttp
- **Serialization**: Kotlinx Serialization
- **Image Loading**: Coil
- **Location Services**: Google Play Services Location
- **Navigation**: Compose Navigation

## Project Structure

The project follows a **multi-module Clean Architecture** approach:

### Architecture Layers

1. **Presentation Layer** (`feature/weather`)
   - ViewModels
   - Compose UI screens
   - UI state management

2. **Domain Layer** (`core/domain`)
   - Use cases (business logic)
   - Domain models
   - Repository interfaces

3. **Data Layer** (`core/data`, `core/network`)
   - Repository implementations
   - Data sources
   - API services
   - Data models (DTOs)

## AI Tools Used

This project was developed with the assistance of **Cursor AI** powered by Claude Sonnet 4.5.
The core logic, architecture decisions, and final implementation were designed and implemented by me,
while AI implemented clean code and whole UI design.

## Acknowledgments

- Weather data provided by [OpenWeatherMap](https://openweathermap.org/)