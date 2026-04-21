package com.xluis.adviceapp.Presentation.Screens.MainScreen

import com.xluis.adviceapp.Presentation.Navitgation.PrincipalScreen

sealed class MainScreenUiEvent {
    data class ChangeScreenClick (val screen : PrincipalScreen) : MainScreenUiEvent()
}