package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.about_info),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://openweathermap.org/"))
            context.startActivity(intent)
        }) {
            Text(stringResource(id = R.string.visit_openweathermap))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(stringResource(id = R.string.select_language))
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                val preferencesManager = PreferencesManager(context)

                DropdownMenuItem(
                    text = { Text("English") },
                    onClick = {
                        preferencesManager.saveLanguage("en")  // Tallenna kieli
                        activity?.let {
                            AppLocale.setLocale(it, "en")
                        }
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Suomi") },
                    onClick = {
                        preferencesManager.saveLanguage("fi")  // Tallenna kieli
                        activity?.let {
                            AppLocale.setLocale(it, "fi")
                        }
                        expanded = false
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("main") }) {
            Text(stringResource(id = R.string.back_to_main))
        }
    }
}
