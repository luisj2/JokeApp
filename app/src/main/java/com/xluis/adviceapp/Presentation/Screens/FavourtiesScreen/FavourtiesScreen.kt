package com.xluis.adviceapp.Presentation.Screens.FavourtiesScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xluis.adviceapp._Domain.Model.Api.Joke

@Composable
fun FavourtiesScreen(
    viewModel : FavouritesScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState

    FavouritesScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FavouritesScreenContent(
    uiState: FavouritesScreenUiState,
    onEvent: (FavouritesScreenUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        // ✅ TÍTULO CENTRADO
        Text(
            text = "❤️ Favoritos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (uiState.favouritesJokes.isEmpty()) {

            // ✅ MENSAJE CENTRADO EN TODA LA PANTALLA
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes favoritos aún 😢")
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.favouritesJokes) { joke ->
                    FavouriteJokeItem(joke = joke)
                }
            }
        }
    }
}

@Composable
fun FavouriteJokeItem(
    joke: Joke
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = "😂 Categoría: ${joke.category}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = joke.setup ?: "No hay chiste",
                style = MaterialTheme.typography.bodyMedium
            )

            if (!joke.delivery.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "👉 ${joke.delivery}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

