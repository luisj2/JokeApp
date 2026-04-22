package com.xluis.adviceapp.Data.Mappers

import com.xluis.adviceapp.Data.Retrofit.Resources.JokeResponse.JokeResponse
import com.xluis.adviceapp.Data.Room.Entities.JokeFavouriteEntity
import com.xluis.adviceapp._Domain.Model.Api.Joke
import com.xluis.adviceapp._Domain.Model.Api.JokeFlag

fun JokeResponse.toDomain(): Joke {
    return Joke(
        id = this.id,
        setup = setup.takeIf { type == "twopart" },
        delivery = delivery.takeIf { type == "twopart" },
        category = this.category,
        flags = this.flags.toDomain()
    )
}

fun Joke.toEntity(): JokeFavouriteEntity {
    return JokeFavouriteEntity(
        id = this.id,
        setup = this.setup ?: "",
        delivery = this.delivery ?: "",
        category = this.category,
        flags = this.flags.map { it.displayName }
    )
}

fun JokeFavouriteEntity.toDomain(): Joke {
    return Joke(
        id = this.id,
        setup = this.setup,
        category = this.category,
        delivery = this.delivery,
        flags = this.flags.mapNotNull { JokeFlag.fromName(it) }
    )
}

