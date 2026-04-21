package com.xluis.adviceapp.Data.Room.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeFavouritesDao {

    //INSERT
    @Insert
    suspend fun insertJokeFavourite(joke : JokeFavouriteEntity) : Long

    //GET

    @Query("SELECT * FROM joke_favourite")
    fun getAllJokeFavourites() : Flow<List<JokeFavouriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM joke_favourite WHERE id = :id)")
    suspend fun jokeFavouriteExist (id : Int) : Boolean

    //DELETE

    @Query("DELETE FROM joke_favourite WHERE id = :id")
    suspend fun deleteJokeFavourite(id : Int) : Int
}