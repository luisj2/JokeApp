package com.xluis.adviceapp.Presentation.Navitgation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class PrincipalScreen(
    val route: Screen,
    val title: String,
    val icon: ImageVector
) {
    JOKE(
        route = Screen.JokeScreen,
        title = "Chistes",
        icon = Icons.Default.Home
    ),

    FAVOURITES(
        route = Screen.FavourtiesScreen,
        title = "Favoritos",
        icon = Icons.Default.Star
    )
}