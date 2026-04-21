package com.xluis.adviceapp.Presentation.Screens.FavourtiesScreen

sealed class FavouritesScrenUiEffect {
    data class ShowToast (val message: String) : FavouritesScrenUiEffect()
}