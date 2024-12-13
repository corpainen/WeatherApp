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

    fun saveCity(city: String) {
        preferences.edit().putString(KEY_CITY, city).apply()
    }

    fun getCity(): String {
        return preferences.getString(KEY_CITY, "Tampere") ?: "Tampere"
    }

    fun saveLanguage(language: String) {
        preferences.edit().putString(KEY_LANGUAGE, language).apply()
    }

    fun getLanguage(): String {
        return preferences.getString(KEY_LANGUAGE, "en") ?: "en"
    }
}
