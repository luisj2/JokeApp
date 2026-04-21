package com.xluis.adviceapp.Presentation.Screens.JokeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
                chargeNewJoke = {onEvent(JokeScreenUiEvent.ChargeNewJoke)}
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

        uiState.avariableJokeCategories.forEach { category ->

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlagSection(
    uiState: JokeScreenUiState,
    onEvent: (JokeScreenUiEvent) -> Unit
) {
    Column {
        Text("Flags", style = MaterialTheme.typography.titleMedium)

        uiState.avariableJokeFlags.forEach { flag ->

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

@Composable
private fun JokeItem(
    joke: Joke,
    chargeNewJoke: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = "😂 Chiste",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = joke.text,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = chargeNewJoke,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Nuevo chiste")
        }
    }
}
