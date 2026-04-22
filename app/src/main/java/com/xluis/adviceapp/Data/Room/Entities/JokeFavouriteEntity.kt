package com.xluis.adviceapp.Data.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_favourite")
data class JokeFavouriteEntity(
    @PrimaryKey val id: Int = 0,
    val setup: String,
    val delivery : String,
    val category: String,
    val flags: List<String>
)
