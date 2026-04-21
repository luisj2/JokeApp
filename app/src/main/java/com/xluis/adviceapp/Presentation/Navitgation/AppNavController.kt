package com.xluis.adviceapp.Presentation.Navitgation

import androidx.navigation.NavHostController

class AppNavController(
    private val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateTo(screen: Screen) {
        navController.navigate(screen)
    }
}