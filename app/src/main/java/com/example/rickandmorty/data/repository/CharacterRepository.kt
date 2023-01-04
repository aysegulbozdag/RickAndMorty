package com.example.rickandmorty.repository

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.network.CharacterApiService
import com.example.rickandmorty.network.CharacterApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepository(private val apiService: CharacterApiService) {

    suspend fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        page: Int?
    ) = apiService.getAllCharacters(characterName, characterStatus, page)

    suspend fun getSingleCharacter(id: Int): Flow<CharacterApiState<Character>> {
        return flow {

            val character = apiService.getSingleCharacter(id)
            emit(CharacterApiState.success(character))

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getEpisode(id: Int): Flow<CharacterApiState<Episode>> {
        return flow {

            val episode = apiService.getEpisode(id)
            emit(CharacterApiState.success(episode))

        }.flowOn(Dispatchers.IO)
    }
}