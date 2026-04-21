package com.xluis.adviceapp.Data.Retrofit.Resources.FlagsResponse

data class JokeFlagsResponse(
    val error: Boolean,
    val flags: List<String>,
    val timestamp: Long
)