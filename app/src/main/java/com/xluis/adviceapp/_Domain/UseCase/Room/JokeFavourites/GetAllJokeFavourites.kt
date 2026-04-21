package com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites

import com.xluis.adviceapp.Data.Room.Repositorys.JokeFavouritesRepository
import javax.inject.Inject

class GetAllJokeFavourites @Inject constructor(
    private val repo: JokeFavouritesRepository
) {
    operator fun invoke() = repo.getAllJokeFavourites()
}