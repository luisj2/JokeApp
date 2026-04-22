package com.xluis.adviceapp._Domain.Model.Api


data class Joke(
    val id: Int,
    val setup: String?,
    val delivery: String?,
    val category: String,
    val flags: List<JokeFlag>,
    val isFavourite : Boolean = false
) {
    fun isSafe(): Boolean {
        return !flags.contains(JokeFlag.NSFW)
    }

    fun hasFlag(flag: JokeFlag): Boolean {
        return flags.contains(flag)
    }

    fun hasAnyFlag(vararg checkFlags: JokeFlag): Boolean {
        return flags.any { it in checkFlags }
    }
}
