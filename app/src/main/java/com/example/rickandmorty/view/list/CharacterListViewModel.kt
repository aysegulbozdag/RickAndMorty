package com.example.rickandmorty.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.datasource.CharacterPagingSource
import com.example.rickandmorty.interfaces.FilterCharacter
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.network.CharacterApi
import com.example.rickandmorty.network.CharacterApiService
import com.example.rickandmorty.network.CharacterApiState
import com.example.rickandmorty.network.Status
import com.example.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.logging.Filter

class CharacterListViewModel : ViewModel() {
    private val repository = CharacterRepository(CharacterApi.RETROFIT_SERVICE)

    fun getListData(name: String?, status: String?) = Pager(PagingConfig(42, maxSize = 200)) {
        CharacterPagingSource(repository, name, status)
    }.flow.cachedIn(viewModelScope)

}