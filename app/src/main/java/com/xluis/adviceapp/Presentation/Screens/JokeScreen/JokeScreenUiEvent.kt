package com.xluis.adviceapp.Presentation.Screens.JokeScreen

import com.xluis.adviceapp._Domain.Model.Api.Joke

sealed class JokeScreenUiEvent {
    data object ChargeNewJoke : JokeScreenUiEvent()

    data class MakeJokeFavorite(val joke: Joke) : JokeScreenUiEvent()
    data class DeselectJokeFavourite (val id : Int) : JokeScreenUiEvent()
    data class SelectCategory(val category: String) : JokeScreenUiEvent()
    data class DeselectCategory(val category: String) : JokeScreenUiEvent()
    data class SelectFlag(val flag: String) : JokeScreenUiEvent()
    data class DeselectFlag(val flag: String) : JokeScreenUiEvent()
}

