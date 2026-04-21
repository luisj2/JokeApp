package com.xluis.adviceapp.Presentation.Navitgation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xluis.adviceapp.Presentation.Screens.FavourtiesScreen.FavourtiesScreen
import com.xluis.adviceapp.Presentation.Screens.JokeScreen.JokeScreen
import com.xluis.adviceapp.Presentation.Screens.MainScreen.MainScreen
import java.security.Principal

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen) {

        composable <Screen.MainScreen>{
            MainScreen(
                screenContent = { ScreenContentByScreen(it) }
            )
        }
    }
}

@Composable
fun ScreenContentByScreen (screen : PrincipalScreen) {
    when(screen){
        PrincipalScreen.JOKE -> JokeScreen()
        PrincipalScreen.FAVOURITES -> FavourtiesScreen()
    }
}