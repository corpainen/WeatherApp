package com.example.weatherapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WeatherRepository {
    /**
     * Fetches weather data for a given city from the OpenWeatherMap API.
     *
     * @param city The city for which to fetch the weather.
     * @return The WeatherResponse object containing weather data or null if an error occurs.
     */
    suspend fun fetchWeather(city: String): WeatherResponse? {
        return withContext(Dispatchers.IO) {
            try {
                // API call using Retrofit
                RetrofitClient.api.getWeather(city, "metric", Constants.API_KEY)
            } catch (e: Exception) {
                e.printStackTrace()
                null // Return null if an error occurs
            }
        }
    }
}
