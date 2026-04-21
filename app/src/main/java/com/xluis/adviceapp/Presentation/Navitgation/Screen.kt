package com.xluis.adviceapp.Presentation.Navitgation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen : Screen()

    @Serializable
    data object JokeScreen : Screen()

    @Serializable
    data object FavourtiesScreen : Screen()
}