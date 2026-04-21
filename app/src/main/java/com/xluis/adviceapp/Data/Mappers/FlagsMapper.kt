package com.xluis.adviceapp.Data.Mappers

import com.xluis.adviceapp.Data.Retrofit.Resources.FlagsResponse.JokeFlagsResponse
import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.Flags
import com.xluis.adviceapp._Domain.Model.Api.JokeFlag

fun Flags.toDomain(): List<JokeFlag> = buildList {
    if (explicit) add(JokeFlag.EXPLICIT)
    if (nsfw) add(JokeFlag.NSFW)
    if (political) add(JokeFlag.POLITICAL)
    if (racist) add(JokeFlag.RACIST)
    if (religious) add(JokeFlag.RELIGIOUS)
    if (sexist) add(JokeFlag.SEXIST)
}

fun JokeFlagsResponse.toList() : List<String> = this.flags