package com.xluis.adviceapp.Data.Mappers

import com.xluis.adviceapp.Data.Retrofit.Resources.CategoriesResponse.JokeCategoriesResponse

fun JokeCategoriesResponse.toList() : List<String> = this.categories