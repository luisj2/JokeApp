package com.xluis.adviceapp.Data.Retrofit.Resources.CategoriesResponse

data class JokeCategoriesResponse(
    val categories: List<String>,
    val categoryAliases: List<CategoryAliase>,
    val error: Boolean,
    val timestamp: Long
)