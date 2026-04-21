package com.xluis.adviceapp._Domain.UseCase.Api

import com.xluis.adviceapp.Data.Retrofit.Usable.JokeRepository
import javax.inject.Inject

class GetJokeFlags @Inject constructor(
    private val repo : JokeRepository
) {
    suspend operator fun invoke() = repo.getJokeFlags()
}