# WeatherApp
# 🚀 Asennusohjeet

1. Vaatimukset
Android Studio (versio 2022.1 tai uudempi)
Android SDK (API-taso 26 tai uudempi)
Internet-yhteys OpenWeatherMap API:n käyttöön

2. OpenWeatherMap API-avaimen hankinta (ei tarvi tehdä koska minun avain on kovakoodattu)
Luo tili OpenWeatherMapin verkkosivustolla.

Luo uusi API-avain ja kopioi se.

Liitä API-avain Constants.kt-tiedostoon:

object Constants {
    const val API_KEY = "OMA_API_AVAIN"
}

3. Projektin kloonaaminen
Kloonaa projekti GitHubista tai siirrä tiedostot paikalliseen hakemistoon:

git clone https://github.com/corpainen/WeatherApp.git

4. Sovelluksen käynnistäminen
Avaa projekti Android Studiossa.
Liitä Android-laite tai käynnistä emulaattori.
Suorita sovellus painamalla "Run" (Shift + F10).

# 🛠️ Käyttöohjeet
Kaupungin valinta:

Kirjoita haluamasi kaupungin nimi ja paina "Get Weather"-painiketta hakeaksesi säätiedot.
Kielenvaihto:

Siirry AboutScreenille painamalla info-ikonia.
Valitse haluamasi kieli (englanti tai suomi).
OpenWeatherMap-sivuston avaaminen:

Paina "Visit OpenWeatherMap" -painiketta AboutScreenillä siirtyäksesi OpenWeatherMapin verkkosivustolle.

# 📋 Laitevaatimukset
Android 8.0 (API 26) tai uudempi
Internet-yhteys säätietojen hakemiseen

# 📝 Tekniset yksityiskohdat
Käytetyt teknologiat:

Kotlin
Jetpack Compose
Retrofit (REST API -kutsuihin)
SharedPreferences (asetusten tallentamiseen)
Tiedostorakenne:

WeatherApp/
├── MainActivity.kt
├── MainScreen.kt
├── AboutScreen.kt
├── AppLocale.kt
├── PreferencesManager.kt
├── WeatherRepository.kt
├── RetrofitClient.kt
├── Constants.kt
├── WeatherResponse.kt
└── res/
    ├── values/strings.xml
    └── values-fi/strings.xml

# 🤝 Tekijä
Joonas Christian Viljami Korpinen

📍 Tampere, Finland
📧 joonas.christian.korpinen@gmail.com
