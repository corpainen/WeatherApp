package com.example.weatherapp

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.WeatherRepository.fetchWeather
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity
    val preferencesManager = PreferencesManager(context)

    var expanded by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf(preferencesManager.getCity()) }
    var weather by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val savedLanguage = preferencesManager.getLanguage()
    val currentLanguage = Locale.getDefault().language

    LaunchedEffect(key1 = savedLanguage) {
        if (currentLanguage != savedLanguage) {
            Log.d("MainScreen", "Vaihdetaan kieli: $savedLanguage")
            preferencesManager.saveLanguage(savedLanguage)  // Tallenna kieli ennen uudelleenkäynnistystä
            activity?.let { AppLocale.setLocale(it, savedLanguage) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = stringResource(id = R.string.select_language))
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("English") },
                                onClick = {
                                    preferencesManager.saveLanguage("en")
                                    activity?.let { AppLocale.setLocale(it, "en") }
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Suomi") },
                                onClick = {
                                    preferencesManager.saveLanguage("fi")
                                    activity?.let { AppLocale.setLocale(it, "fi") }
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("about")
            }) {
                Icon(Icons.Default.Info, contentDescription = stringResource(id = R.string.about_info))
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(stringResource(id = R.string.city)) },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = weather.ifEmpty { stringResource(id = R.string.get_weather) },
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    preferencesManager.saveCity(city)
                    val result = fetchWeather(city)
                    weather = if (result != null) {
                        val tempString = String.format(Locale.getDefault(), "%.1f", result.main.temp)
                        context.getString(
                            R.string.weather_info,
                            result.name,
                            result.weather[0].description,
                            tempString
                        )
                    } else {
                        context.getString(
                            R.string.error_message,
                            city,
                            "Data not found"
                        )
                    }

                }
            }) {
                Text(stringResource(id = R.string.get_weather))
            }
        }
    }
}

