package com.example.rickandmorty.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.CharacterPagingSource
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.RickAndMortyApi
import com.example.rickandmorty.network.RickAndMortyApiService
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val service: RickAndMortyApiService,
) : CharacterRepository {

    override  fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 42, prefetchDistance = 2),
        pagingSourceFactory = { CharacterPagingSource(service) }
    ).flow

    override suspend fun getSingleCharacter(id: Int) = service.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: List<Int>) = service.getMultipleCharacters(ids)

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) = service.filterCharacters(name, status, species, type, gender)
}