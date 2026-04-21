package com.xluis.adviceapp.Data.Retrofit.Usable

import com.xluis.adviceapp.Data.Retrofit.Resources.CategoriesResponse.JokeCategoriesResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.FlagsResponse.JokeFlagsResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.JokeResponse
import com.xluis.adviceapp._Domain.Model.Results.SuspendResult

interface JokeQuerys {
    suspend fun getJokeByCategory(jokeCategories : List<String>) : SuspendResult<JokeResponse>
    suspend fun getJokeCategories(): SuspendResult<JokeCategoriesResponse>
    suspend fun getJokeFlags () : SuspendResult<JokeFlagsResponse>


}