package com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse

data class JokeResponse(
    val category: String,
    val delivery: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)