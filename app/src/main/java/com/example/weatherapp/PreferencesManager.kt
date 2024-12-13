package com.example.weatherapp

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("WeatherAppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_CITY = "city"
        private const val KEY_LANGUAGE = "language"
    }

    // Tallenna kaupunki
    fun saveCity(city: String) {
        preferences.edit().putString(KEY_CITY, city).apply()
    }

    // Hae tallennettu kaupunki
    fun getCity(): String {
        return preferences.getString(KEY_CITY, "Tampere") ?: "Tampere"
    }

    // Tallenna kieli
    fun saveLanguage(language: String) {
        preferences.edit().putString(KEY_LANGUAGE, language).apply()
    }

    // Hae tallennettu kieli
    fun getLanguage(): String {
        return preferences.getString(KEY_LANGUAGE, "en") ?: "en"
    }
}
