package com.example.rickandmorty.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.db.entity.FavCharacter

@Dao
interface FavCharacterDAO {

    @Insert
    fun insertFavCharacter(favCha: FavCharacter)

    @Query("Select * from favCharacter WHERE id  = :id")
    fun getFavCharacter(id: String) : LiveData<FavCharacter>

    @Query("Update favCharacter set isFav=:isFav  WHERE id = :id")
    fun updateCharacter(id:String, isFav: Boolean)

}