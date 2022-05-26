package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.RickAndMortyApi
import com.example.rickandmorty.repository.CharacterRepositoryImpl
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class RamListViewModel(): ViewModel() {
    private val _status = MutableLiveData<String>()
    val status : LiveData<String> = _status
    private var repository: CharacterRepositoryImpl = CharacterRepositoryImpl(RickAndMortyApi.RETROFIT_SERVICE)

    val userItemsUiStates = repository.getAllCharacters()
        .map { pagingData ->
            pagingData.map { userModel -> Character(userModel.id,userModel.name,userModel.status,userModel.species,userModel.type,
            userModel.gender,userModel.image,userModel.url,userModel.origin,userModel.location,userModel.episode) }
        }.cachedIn(viewModelScope)




}