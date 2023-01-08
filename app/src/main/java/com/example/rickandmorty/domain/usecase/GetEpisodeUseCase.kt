package com.example.rickandmorty.domain

import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.data.remote.State
import com.example.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEpisodeUseCase(private val repository: CharacterRepository) {
    operator fun invoke(id: Int): Flow<State<Episode>> = flow {
        try {
            emit(State.loading())
            val episode = repository.getEpisode(id)
            emit(State.success(episode))
        } catch (e: Exception) {
            emit(State.error(e.message.toString()))
        }
    }
}