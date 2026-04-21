package com.xluis.adviceapp.Presentation.Screens.MainScreen

import com.xluis.adviceapp.Presentation.BaseScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : BaseScreenViewModel<MainScreenUiState, MainScreenUiEvent, MainScreenUiEffect>(initialState = MainScreenUiState()) {
    override fun onEvent(event: MainScreenUiEvent) {
        when(event){
            is MainScreenUiEvent.ChangeScreenClick -> updateState { copy(screenSelected = event.screen)}
        }
    }
}