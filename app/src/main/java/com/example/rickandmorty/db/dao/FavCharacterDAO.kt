package com.example.rickandmorty.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rickandmorty.db.entity.FavCharacter

@Dao
interface FavCharacterDAO {

    @Insert
    fun insertFavCharacter(favCha: FavCharacter)

    @Query("Select * from favCharacter WHERE id  = :id")
    fun getFavCharacter(id: String) : LiveData<FavCharacter>

}