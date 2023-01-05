package com.example.rickandmorty.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favCharacter")
data class FavCharacter(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "isFav")
    val isFav: Boolean,
)