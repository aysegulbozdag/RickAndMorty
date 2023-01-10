package com.example.rickandmorty.view.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.datasource.CharacterPagingSource
import com.example.rickandmorty.data.remote.CharacterApi
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.usecase.GetAllCharactersUseCase

class CharacterListViewModel : ViewModel() {
    private val repository = GetAllCharactersUseCase(CharacterRepositoryImpl(CharacterApi.RETROFIT_SERVICE))

    fun getListData(name: String?, status: String?) = Pager(PagingConfig(42, maxSize = 200)) {
        CharacterPagingSource(repository, name, status)
    }.flow.cachedIn(viewModelScope)

}