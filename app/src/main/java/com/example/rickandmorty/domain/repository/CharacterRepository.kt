package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.data.model.RickyAndMortList

interface CharacterRepository {

    suspend fun getAllCharacters(
        characterName: String?,
        characterStatus: String?,
        page: Int?
    ): RickyAndMortList

    suspend fun getEpisode(id: Int): Episode

    suspend fun getCharacter(id: Int): Character
}