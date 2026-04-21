package com.xluis.adviceapp.Data.Room

import android.content.Context
import androidx.room.Room
import com.xluis.adviceapp.Data.Room.DAOs.JokeFavouritesDao
import com.xluis.adviceapp._Domain.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): JokeDatabase {
        return Room.databaseBuilder(
            context,
            JokeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideJokeDao(
        database: JokeDatabase
    ): JokeFavouritesDao {
        return database.jokeFavouritesDao()
    }
}