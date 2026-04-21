package com.xluis.adviceapp._Domain.UseCase.Api

import com.xluis.adviceapp.Data.Retrofit.Usable.JokeRepository
import javax.inject.Inject

class GetJokeCategories @Inject constructor(
    private val repo : JokeRepository
) {
    suspend operator fun invoke() = repo.getJokeCategories()
}