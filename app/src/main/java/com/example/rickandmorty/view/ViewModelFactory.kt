package com.example.rickandmorty.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.view.detail.CharacterDetailViewModel

class ViewModelFactory(
    private val application: Context?,
    private val characterId: String,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return application?.let { CharacterDetailViewModel(it, characterId) } as T
    }
}