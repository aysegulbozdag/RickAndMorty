package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.remote.State
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCharacterUseCase(private val repository: CharacterRepositoryImpl) {

suspend operator fun invoke(id :Int): Flow<State<Character>> = flow {
        try {
            emit(State.loading())
            val characters = repository.getCharacter(id)
            emit(State.success(characters))
        }catch (e:HttpException){
            emit(State.error(e.localizedMessage ?: "An unexpected error occured" ))
        }catch (e: IOException){
            emit(State.error("Couldn't reach server. Check your internet connection."))
        }
    }
}