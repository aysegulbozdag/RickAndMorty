package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.RickyAndMortList
import com.example.rickandmorty.data.remote.State
import com.example.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCharactersUseCase(private val repository: CharacterRepository) {

    operator fun invoke(
        characterName: String?,
        characterStatus: String?,
        page: Int?
    ): Flow<State<RickyAndMortList>> = flow {
        try {
            emit(State.loading())
            val getAllCharacters = repository.getAllCharacters(characterName, characterStatus, page)
            emit(State.success(getAllCharacters))
        } catch (e: Exception) {
            emit(State.error(e.message.toString()))
        }
    }
}