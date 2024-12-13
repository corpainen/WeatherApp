package com.example.weatherapp

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import java.util.Locale

object AppLocale {
    fun setLocale(activity: Activity, language: String) {
        val currentLocale = Locale.getDefault().language
        if (currentLocale == language) {
            Log.d("AppLocale", "Kieli on jo asetettu: $language")
            return
        }

        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)

        activity.resources.updateConfiguration(config, activity.resources.displayMetrics)

        // Käynnistä aktiviteetti uudelleen vain kerran
        Log.d("AppLocale", "Kielen vaihto: $language")
        activity.recreate()
    }
}
