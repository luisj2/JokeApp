package com.xluis.adviceapp.Data.Retrofit

import com.xluis.adviceapp.Data.Retrofit.Resources.CategoriesResponse.JokeCategoriesResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.FlagsResponse.JokeFlagsResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.JokeResponse
import com.xluis.adviceapp._Domain.util.CATEGORIES_ENDPOINT
import com.xluis.adviceapp._Domain.util.FLAGS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path


interface JokeApi {
    @GET("joke/{categories}")
    suspend fun getJokeByCategory(
        @Path("categories") categories: String = "Any"
    ): JokeResponse

    @GET(CATEGORIES_ENDPOINT)
    suspend fun getCategories(): JokeCategoriesResponse

    @GET(FLAGS_ENDPOINT)
    suspend fun getFlags(): JokeFlagsResponse
}