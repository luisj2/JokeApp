package com.xluis.adviceapp.Presentation.Screens.JokeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xluis.adviceapp._Domain.Model.Api.Joke
import com.xluis.adviceapp._Domain.util.showToast


@Composable
fun JokeScreen(
    viewModel: JokeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is JokeScreenUiEffect.ShowToast -> {
                    context.showToast(effect.message)
                }
            }
        }
    }

    JokeScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun JokeScreenContent(
    uiState: JokeScreenUiState,
    onEvent: (JokeScreenUiEvent) -> Unit
) {
    val joke = uiState.joke

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CategorySection(uiState, onEvent)

        Spacer(modifier = Modifier.height(12.dp))

        FlagSection(uiState, onEvent)
        if (joke == null) {
            Text("Cargando chiste...")
        } else {
            JokeItem(
                joke = joke,
                chargeNewJoke = { onEvent(JokeScreenUiEvent.ChargeNewJoke) },
                onFavoriteJoke = {joke->
                    if(joke.isFavourite)
                        onEvent(JokeScreenUiEvent.DeselectJokeFavourite(joke.id))
                    else
                    onEvent(JokeScreenUiEvent.MakeJokeFavorite(joke))

                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategorySection(
    uiState: JokeScreenUiState,
    onEvent: (JokeScreenUiEvent) -> Unit
) {
    Column {
        Text("Categorías", style = MaterialTheme.typography.titleMedium)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(uiState.avariableJokeCategories) { category ->

                val selected = uiState.selectedJokeCategories.contains(category)

                FilterChip(
                    selected = selected,
                    onClick = {
                        onEvent(
                            if (selected)
                                JokeScreenUiEvent.DeselectCategory(category)
                            else
                                JokeScreenUiEvent.SelectCategory(category)
                        )
                    },
                    label = { Text(category) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlagSection(
    uiState: JokeScreenUiState,
    onEvent: (JokeScreenUiEvent) -> Unit
) {
    Column {
        Text("Flags", style = MaterialTheme.typography.titleMedium)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(uiState.avariableJokeFlags) { flag ->

                val selected = uiState.selectedJokeFlags.contains(flag)

                FilterChip(
                    selected = selected,
                    onClick = {
                        onEvent(
                            if (selected)
                                JokeScreenUiEvent.DeselectFlag(flag)
                            else
                                JokeScreenUiEvent.SelectFlag(flag)
                        )
                    },
                    label = { Text(flag) }
                )
            }
        }
    }
}

@Composable
private fun JokeItem(
    joke: Joke,
    chargeNewJoke: () -> Unit,
    onFavoriteJoke: (joke: Joke) -> Unit
) {
    var showDelivery by remember { mutableStateOf(false) }

    if(joke.setup.isNullOrBlank()) chargeNewJoke()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = { onFavoriteJoke(joke) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (joke.isFavourite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "Favorito"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "😂 Chiste",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = joke.setup ?: "Cargando chiste...",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (!joke.delivery.isNullOrBlank()) {

                    if (showDelivery) {
                        Text(
                            text = joke.delivery ?: "",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        Button(
                            onClick = { showDelivery = true }
                        ) {
                            Text("Ver respuesta 😂")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = {
                        showDelivery = false
                        chargeNewJoke()
                    }
                ) {
                    Text("Nuevo chiste")
                }
            }
        }
    }
}
