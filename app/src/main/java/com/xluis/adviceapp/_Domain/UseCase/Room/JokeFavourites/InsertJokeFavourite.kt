package com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites

import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity
import com.xluis.adviceapp.Data.Room.Repositorys.JokeFavouritesRepository
import javax.inject.Inject

class InsertJokeFavourite @Inject constructor(
    private val repository: JokeFavouritesRepository
) {
    suspend operator fun invoke(joke: JokeFavouriteEntity) = repository.insertJokeFavourite(joke)
}
