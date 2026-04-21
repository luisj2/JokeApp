package com.xluis.adviceapp.Presentation.Screens.FavourtiesScreen

import com.xluis.adviceapp._Domain.Model.Api.Joke

data class FavouritesScreenUiState(
    val favouritesJokes : List<Joke> = emptyList()
)
