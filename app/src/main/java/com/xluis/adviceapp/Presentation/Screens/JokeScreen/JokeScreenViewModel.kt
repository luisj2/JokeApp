package com.xluis.adviceapp.Presentation.Screens.JokeScreen

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xluis.adviceapp.Data.Mappers.toDomain
import com.xluis.adviceapp.Data.Mappers.toEntity
import com.xluis.adviceapp.Data.Mappers.toList
import com.xluis.adviceapp.Presentation.BaseScreenViewModel
import com.xluis.adviceapp._Domain.Model.Api.Joke
import com.xluis.adviceapp._Domain.Model.Results.SuspendResult
import com.xluis.adviceapp._Domain.UseCase.Api.GetJokeByCategory
import com.xluis.adviceapp._Domain.UseCase.Api.GetJokeCategories
import com.xluis.adviceapp._Domain.UseCase.Api.GetJokeFlags
import com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites.DeleteJokeFavouriteById
import com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites.InsertJokeFavourite
import com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites.IsJokeFavourite
import com.xluis.adviceapp._Domain.util.onError
import com.xluis.adviceapp._Domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeScreenViewModel @Inject constructor(
    private val getJokeByCategory: GetJokeByCategory,
    private val getJokeCategories: GetJokeCategories,
    private val getJokeFlags: GetJokeFlags,
    private val InsertJokeFavourite: InsertJokeFavourite,
    private val IsJokeFavourite: IsJokeFavourite,
    private val deleteJokeFavourite: DeleteJokeFavouriteById
) : BaseScreenViewModel<JokeScreenUiState, JokeScreenUiEvent, JokeScreenUiEffect>
    (JokeScreenUiState()) {

    init {
        showToast = ::showToast
        chargeJokeByCategory()
        chargeJokeCategories()
        chargeJokeFlags()
    }


    override fun onEvent(event: JokeScreenUiEvent) {
        when (event) {
            is JokeScreenUiEvent.ChargeNewJoke -> chargeJokeByCategory()
            is JokeScreenUiEvent.DeselectCategory -> updateState {
                copy(
                    selectedJokeCategories = avariableJokeCategories.minus(
                        event.category
                    )
                )
            }

            is JokeScreenUiEvent.DeselectFlag -> updateState {
                copy(
                    selectedJokeFlags = avariableJokeFlags.minus(
                        event.flag
                    )
                )
            }

            is JokeScreenUiEvent.SelectCategory -> updateState {
                copy(
                    selectedJokeCategories = avariableJokeCategories.plus(
                        event.category
                    )
                )
            }

            is JokeScreenUiEvent.SelectFlag -> updateState {
                copy(
                    selectedJokeFlags = avariableJokeFlags.plus(
                        event.flag
                    )
                )
            }

            is JokeScreenUiEvent.MakeJokeFavorite -> insertJokeFavourite(event.joke)
            is JokeScreenUiEvent.DeselectJokeFavourite -> removeJokeFavourite()
        }
    }

    private fun insertJokeFavourite(joke: Joke) {
        viewModelScope.launch {
            InsertJokeFavourite(joke.toEntity())
                .onSuccess { showToast("Chiste añadido a favoritos") }
                .onError { handleError(it) }
        }
    }

    private fun removeJokeFavourite() {
        viewModelScope.launch {
            val jokeId = _uiState.value.joke?.id
            if(jokeId == null){
                showToast("No se ha encontrado el chiste")
                return@launch
            }
            deleteJokeFavourite(jokeId)
                .onSuccess {
                    isJokeFavourite(jokeId)
                    showToast("Chiste eliminado de favoritos")
                }
                .onError { handleError(it) }
        }
    }

    private fun chargeJokeByCategory(
    ) {
        val jokeCategories = _uiState.value.avariableJokeCategories

        viewModelScope.launch {
            getJokeByCategory(jokeCategories)
                .onSuccess { joke ->

                    val jokeDomain = joke.toDomain()

                    updateState {
                        copy(joke = jokeDomain)
                    }

                    isJokeFavourite(jokeDomain.id)

                }
                .onError { handleError(it) }
        }
    }

    private fun isJokeFavourite(id: Int) {
        viewModelScope.launch {
            IsJokeFavourite(id)
                .onSuccess { updateState { copy(joke = joke?.copy(isFavourite = it)) } }
        }
    }

    private fun chargeJokeCategories() {
        viewModelScope.launch {
            getJokeCategories()
                .onSuccess { updateState { copy(avariableJokeCategories = it.toList()) } }
                .onError { handleError(it) }
        }
    }

    private fun chargeJokeFlags() {
        viewModelScope.launch {
            getJokeFlags()
                .onSuccess { updateState { copy(avariableJokeFlags = it.toList()) } }
                .onError { handleError(it) }
        }
    }


    private fun showToast(message: String) {
        sendEffect(JokeScreenUiEffect.ShowToast(message))
    }

    private fun handleError(error: SuspendResult.Error) {
        showToast(error.message)
    }
}
