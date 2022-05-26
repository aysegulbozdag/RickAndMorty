package com.example.rickandmorty

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.network.RickAndMortyApiService
import com.example.rickandmorty.repository.CharacterRepositoryImpl
import com.example.rickandmorty.viewmodel.RamListViewModel

class ViewModelFactoryy (repo : CharacterRepositoryImpl) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RamListViewModel::class.java)) {
            return RamListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }

}