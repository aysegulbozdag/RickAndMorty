package com.example.rickandmorty.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val application: Context?,
    private val characterId: String,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return application?.let { CharacterDetailViewModel(it, characterId) } as T
    }
}