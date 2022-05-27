package com.example.rickandmorty.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.datasource.CharacterPagingSource
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.CharacterApi
import com.example.rickandmorty.network.CharacterApiService
import kotlinx.coroutines.flow.Flow

class CharacterListViewModel : ViewModel() {
    var retroService: CharacterApiService = CharacterApi.RETROFIT_SERVICE

    fun getListData(): Flow<PagingData<Character>> =  Pager(
            config = PagingConfig(pageSize = 42, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) })
            .flow.cachedIn(viewModelScope)

}