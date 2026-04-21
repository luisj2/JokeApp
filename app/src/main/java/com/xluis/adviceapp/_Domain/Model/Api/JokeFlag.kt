package com.xluis.adviceapp._Domain.Model.Api

enum class JokeFlag(val displayName: String) {
    NSFW("Not Safe For Work"),
    EXPLICIT("Explícito"),
    POLITICAL("Político"),
    RACIST("Racista"),
    RELIGIOUS("Religioso"),
    SEXIST("Sexista");

    companion object{
        fun fromName(name: String): JokeFlag? {
            return entries.find {
                it.name.equals(name, ignoreCase = true) ||
                        it.displayName.equals(name, ignoreCase = true)
            }
        }
    }


}