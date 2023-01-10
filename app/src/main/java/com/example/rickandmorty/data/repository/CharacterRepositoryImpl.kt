package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.data.model.RickyAndMortList
import com.example.rickandmorty.data.remote.CharacterApiService
import com.example.rickandmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(private val apiService: CharacterApiService) : CharacterRepository {

    override suspend fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        page: Int?
    ): RickyAndMortList = apiService.getAllCharacters(characterName, characterStatus, page)

    override suspend fun getCharacter(id: Int): Character = apiService.getSingleCharacter(id)

    override suspend fun getEpisode(id: Int): Episode = apiService.getEpisode(id)


}