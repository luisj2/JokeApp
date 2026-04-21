package com.xluis.adviceapp.Presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


abstract class BaseScreenViewModel<S, Event, F>(
    initialState: S
) : ViewModel() {

    protected val _uiState = mutableStateOf(initialState)
    val uiState: State<S> = _uiState

    protected val _uiEffect = Channel<F>()
    val uiEffect = _uiEffect.receiveAsFlow()

    protected var showToast: (String) -> Unit = {}

    protected fun updateState(update: S.() -> S) {
        _uiState.value = _uiState.value.update()
    }

    protected fun sendEffect(effect: F) {
        viewModelScope.launch {
            _uiEffect.send(effect)
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        onError: ((Throwable) -> Unit)? = null,
        onCollect: (T) -> Unit
    ) {
        viewModelScope.launch {
            flow
                .catch { error ->
                    if (onError != null) {
                        onError(error)
                    } else {
                        showToast(error.message ?: "Error desconocido")
                    }
                }
                .collect { value -> onCollect(value) }
        }
    }

    abstract fun onEvent(event: Event)
}