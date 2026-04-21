package com.xluis.adviceapp.Data.Room

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.xluis.adviceapp._Domain.Model.Results.SuspendResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRoomRepository {
    protected suspend fun <T> executeRoomOperation(block: suspend () -> T): SuspendResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                SuspendResult.Success(block())
            } catch (e: Exception) {
                SuspendResult.Error(RoomExceptionHandler.handle(e))
            }
        }
    }


    protected suspend fun <T> executeRoomTransaction(
        database: RoomDatabase,
        block: suspend () -> T
    ): SuspendResult<T> {
        return executeRoomOperation {
            database.withTransaction {
                block()
            }
        }
    }
}