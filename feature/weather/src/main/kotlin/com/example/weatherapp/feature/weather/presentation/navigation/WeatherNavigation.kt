package com.example.weatherapp.feature.weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.feature.weather.presentation.WeatherViewModel
import com.example.weatherapp.feature.weather.presentation.screen.SearchScreen
import com.example.weatherapp.feature.weather.presentation.screen.WeatherScreen

sealed class Screen(val route: String) {
    data object Weather : Screen("weather")
    data object Search : Screen("search")
}

@Composable
fun WeatherNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val sharedViewModel: WeatherViewModel = hiltViewModel()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(Screen.Weather.route) {
            WeatherScreen(
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                },
                viewModel = sharedViewModel
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onLocationSelected = {
                    navController.popBackStack()
                },
                viewModel = sharedViewModel
            )
        }
    }
}
