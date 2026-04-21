package com.xluis.adviceapp.Data.Retrofit.Usable

object ApiError {
    fun handle(throwable: Throwable): String {
        return when (throwable) {

            is java.net.UnknownHostException ->
                "Sin conexión a Internet"

            is java.net.SocketTimeoutException ->
                "Tiempo de espera agotado"

            is retrofit2.HttpException -> {
                when (throwable.code()) {
                    400 -> "Petición incorrecta"
                    401 -> "No autorizado"
                    403 -> "Acceso prohibido"
                    404 -> "Recurso no encontrado"
                    500 -> "Error del servidor"
                    else -> "Error HTTP desconocido (${throwable.code()})"
                }
            }

            else -> throwable.message ?: "Error desconocido"
        }
    }
}