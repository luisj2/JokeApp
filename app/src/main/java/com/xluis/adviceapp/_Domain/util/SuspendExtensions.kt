package com.xluis.adviceapp._Domain.util

import com.xluis.adviceapp._Domain.Model.Results.SuspendResult


fun <T> SuspendResult<T>.getOrNull(): T? {
    return when (this) {
        is SuspendResult.Success -> data
        else -> null
    }
}

fun SuspendResult<Boolean>.successOrFalse () : Boolean{
    return when(this){
        is SuspendResult.Success -> this.data
        else-> false
    }
}







inline fun <T, R> SuspendResult<T>.flatMap(
    transform: (T) -> SuspendResult<R>
): SuspendResult<R> = when (this) {
    is SuspendResult.Success -> transform(this.data)
    is SuspendResult.Error -> this
    else -> SuspendResult.Error("Estado inválido")
}
inline fun <T> runSuspendCatching(
    block: () -> T
): SuspendResult<T> =
    try {
        SuspendResult.Success(block())
    } catch (e: Exception) {
        SuspendResult.Error(e.message ?: "Error inesperado")
    }

inline fun <T, R> SuspendResult<T>.map(
    transform: (T) -> R
): SuspendResult<R> = when (this) {
    is SuspendResult.Success -> SuspendResult.Success(transform(data))
    is SuspendResult.Error -> this
    SuspendResult.Loading -> SuspendResult.Loading
}

inline fun <T> SuspendResult<T>.onSuccess(action: (T) -> Unit): SuspendResult<T> {
    if (this is SuspendResult.Success) action(data)
    return this
}

inline fun <T> SuspendResult<T>.onError(action: (SuspendResult.Error) -> Unit): SuspendResult<T> {
    if (this is SuspendResult.Error) action(this)
    return this
}

inline fun <T> SuspendResult<T>.showErrorToast(show: (String?) -> Unit): SuspendResult<T> {
    if (this is SuspendResult.Error) show(this.message)
    return this
}
fun SuspendResult<*>.orThrow() {
    if (this is SuspendResult.Error) {
        throw Exception(this.message)
    }
}





fun <T> SuspendResult<List<T>>.getListOrEmpty(): List<T>  {
    return when (this) {
        is SuspendResult.Success -> data
        else -> emptyList()
    }
}

inline fun <T, R> SuspendResult<T>.fold(
    onSuccess: (T) -> R,
    onError: (message : String) -> R
): R? = when (this) {
    is SuspendResult.Success -> onSuccess(data)
    is SuspendResult.Error -> onError(this.message)
    else -> null
}
