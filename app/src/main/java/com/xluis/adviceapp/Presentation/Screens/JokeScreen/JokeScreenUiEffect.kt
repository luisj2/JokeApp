package com.xluis.adviceapp.Presentation.Screens.JokeScreen

sealed class JokeScreenUiEffect {
    data class ShowToast (val message : String) : JokeScreenUiEffect()
}