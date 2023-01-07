package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.RickyAndMortList
import com.example.rickandmorty.data.network.CharacterApiState
import com.example.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCharacterUseCase(private val repository: CharacterRepository) {

    operator fun invoke(): Flow<CharacterApiState<RickyAndMortList>> = flow {
        try {
            emit(CharacterApiState.loading())
            val characters = repository.getAllCharacters(null,null,1)
            emit(CharacterApiState.success(characters))
        }catch (e:HttpException){
            emit(CharacterApiState.error(e.localizedMessage ?: "An unexpected error occured" ))
        }catch (e: IOException){
            emit(CharacterApiState.error("Couldn't reach server. Check your internet connection."))
        }
    }

}