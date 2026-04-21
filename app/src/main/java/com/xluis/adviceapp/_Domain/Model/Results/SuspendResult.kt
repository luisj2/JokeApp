package com.xluis.adviceapp._Domain.Model.Results

sealed class SuspendResult<out T> {

    data class Success<T>(
        val data: T
    ) : SuspendResult<T>()

    data class Error(
        val message: String
    ) : SuspendResult<Nothing>()

    object Loading : SuspendResult<Nothing>()
}