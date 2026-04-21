package com.xluis.adviceapp._Domain.UseCase.Room.JokeFavourites

import com.xluis.adviceapp.Data.Room.Repositorys.JokeFavouritesRepository
import javax.inject.Inject

class IsJokeFavourite @Inject constructor(
    private val repo : JokeFavouritesRepository
){
    suspend operator fun invoke(id : Int) = repo.jokeFavouriteExist(id)
}