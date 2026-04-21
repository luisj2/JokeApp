package com.xluis.adviceapp.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xluis.adviceapp.Data.Room.DAOs.JokeFavouritesDao
import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity

@Database(
    entities = [JokeFavouriteEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeFavouritesDao(): JokeFavouritesDao
}
