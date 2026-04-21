package com.xluis.adviceapp.Presentation.Screens.JokeScreen

import com.xluis.adviceapp._Domain.Model.Api.Joke

data class JokeScreenUiState(
    val isLoading : Boolean = false,
    val joke : Joke? = null,
    val avariableJokeCategories : List<String> = emptyList(),
    val avariableJokeFlags : List<String> = emptyList(),
    val selectedJokeCategories : List<String> = emptyList(),
    val selectedJokeFlags : List<String> = emptyList()
)
