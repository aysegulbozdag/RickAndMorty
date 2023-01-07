package com.example.rickandmorty.view.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.datasource.CharacterPagingSource
import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.domain.GetCharacterUseCase

class CharacterListViewModel : ViewModel() {
    private val repository = GetCharacterUseCase( CharacterRepository(CharacterApi.RETROFIT_SERVICE))

    fun getListData(name: String?, status: String?) = Pager(PagingConfig(42, maxSize = 200)) {
        CharacterPagingSource(repository, name, status)
    }.flow.cachedIn(viewModelScope)

}