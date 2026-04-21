package com.xluis.adviceapp.Data.Mappers

import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.Flags
import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.JokeResponse
import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity
import com.xluis.adviceapp._Domain.Model.Api.Joke
import com.xluis.adviceapp._Domain.Model.Api.JokeFlag

fun JokeResponse.toDomain(): Joke {
    return Joke(
        id = this.id,
        text = this.setup,
        category = this.category,
        flags = this.flags.toDomain()
    )
}

fun Joke.toEntity(): JokeFavouriteEntity {
    return JokeFavouriteEntity(
        id = this.id,
        text = this.text,
        category = this.category,
        flags = this.flags.map { it.displayName }
    )
}

fun JokeFavouriteEntity.toDomain(): Joke {
    return Joke(
        id = this.id,
        text = this.text,
        category = this.category,
        flags = this.flags.mapNotNull { JokeFlag.fromName(it) }
    )
}

