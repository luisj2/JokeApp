package com.xluis.adviceapp.Data.Retrofit.Usable

import com.xluis.adviceapp._Domain.Model.Results.SuspendResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> executeApiOperation(
        block: suspend () -> T
    ): SuspendResult<T> {

        return withContext(Dispatchers.IO) {

            try {
                val result = block()
                SuspendResult.Success(result)

            } catch (e: Exception) {
                SuspendResult.Error(
                    message = ApiError.handle(e)
                )
            }
        }
    }
}