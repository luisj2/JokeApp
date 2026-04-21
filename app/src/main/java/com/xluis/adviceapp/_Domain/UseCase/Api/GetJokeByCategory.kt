package com.xluis.adviceapp._Domain.UseCase.Api

import com.xluis.adviceapp.Data.Retrofit.Usable.JokeRepository
import javax.inject.Inject

class GetJokeByCategory @Inject constructor(
    private val repository: JokeRepository
) {
    suspend operator fun invoke(
        jokeCategories : List<String>
    ) = repository.getJokeByCategory(jokeCategories)
}
