package com.example.rickandmorty.repository

import androidx.paging.PagingData
import com.example.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
     fun getAllCharacters(): Flow<PagingData<Character>>
    suspend fun getSingleCharacter(id: Int)
    suspend fun getMultipleCharacters(ids: List<Int>)
    suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    )
}