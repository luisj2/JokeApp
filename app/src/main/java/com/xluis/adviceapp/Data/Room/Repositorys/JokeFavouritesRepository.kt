package com.xluis.adviceapp.Data.Room.Repositorys

import com.xluis.adviceapp.Data.Room.BaseRoomRepository
import com.xluis.adviceapp.Data.Room.DAOs.JokeFavouritesDao
import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity
import com.xluis.adviceapp._Domain.Model.Results.SuspendResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeFavouritesRepository @Inject constructor(
    private val dao: JokeFavouritesDao
) : BaseRoomRepository() {

    suspend fun insertJokeFavourite(
        joke: JokeFavouriteEntity
    ): SuspendResult<Boolean> {
        return executeRoomOperation {
            dao.insertJokeFavourite(joke) > 0
        }
    }

    fun getAllJokeFavourites(): Flow<List<JokeFavouriteEntity>> {
        return dao.getAllJokeFavourites()
    }

    suspend fun jokeFavouriteExist (id : Int) : SuspendResult<Boolean>{
        return executeRoomOperation {
            dao.jokeFavouriteExist(id)
        }
    }

    suspend fun deleteJokeFavourite(id: Int): SuspendResult<Boolean> {
        return executeRoomOperation {
            dao.deleteJokeFavourite(id) > 0
        }
    }
}
