package com.xluis.adviceapp.Data.Retrofit.Usable

import com.xluis.adviceapp.Data.Retrofit.JokeApi
import com.xluis.adviceapp.Data.Retrofit.Resources.CategoriesResponse.JokeCategoriesResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.FlagsResponse.JokeFlagsResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.JokeResponse
import com.xluis.adviceapp._Domain.Model.Results.SuspendResult
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val api: JokeApi
) : JokeQuerys, BaseRepository() {
    override suspend fun getJokeByCategory(
        jokeCategories: List<String>
    ): SuspendResult<JokeResponse> {
        return executeApiOperation {
            if (jokeCategories.isNullOrEmpty()) api.getJokeByCategory()
            else api.getJokeByCategory(jokeCategories.joinToString(","))
        }
    }

    override suspend fun getJokeCategories(): SuspendResult<JokeCategoriesResponse> {
        return executeApiOperation { api.getCategories() }
    }

    override suspend fun getJokeFlags(): SuspendResult<JokeFlagsResponse> {
        return executeApiOperation { api.getFlags() }
    }
}