package com.example.rickandmorty.repository

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.CharacterApiService
import com.example.rickandmorty.network.CharacterApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepository(private val apiService: CharacterApiService) {
    suspend fun getComment(id: Int): Flow<CharacterApiState<Character>> {
        return flow {

            val comment=apiService.getSingleCharacter(id)

            emit(CharacterApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}