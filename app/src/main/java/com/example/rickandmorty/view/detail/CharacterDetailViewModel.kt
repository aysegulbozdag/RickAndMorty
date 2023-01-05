package com.example.rickandmorty.view.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.db.FavCharacterInstance
import com.example.rickandmorty.data.db.dao.FavCharacterDAO
import com.example.rickandmorty.data.db.entity.FavCharacter
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.rickandmorty.data.network.*
import com.example.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch

class CharacterDetailViewModel(private val context: Context, private val characterId: String) : ViewModel() {

    private val repository = CharacterRepository(
        CharacterApi.RETROFIT_SERVICE
    )

    val characterState = MutableStateFlow(
        CharacterApiState(
            Status.LOADING,
            Character(), ""
        )
    )

    val episodeState = MutableStateFlow(
        CharacterApiState(
            Status.LOADING,
            Episode(), ""
        )
    )

    private lateinit var favCharacterDAO: FavCharacterDAO
    lateinit var getFavCharacter: LiveData<FavCharacter>

    init {
        val favCharacterDB = FavCharacterInstance.getDatabase(context)
        if (favCharacterDB != null) {
            favCharacterDAO = favCharacterDB.favCharacterDao()
        }

        getCharacter()
        getEpisode()
        getFavCharacter()
    }


        private fun getCharacter() {
            characterState.value = CharacterApiState.loading()
            viewModelScope.launch {
                repository.getSingleCharacter(characterId.toInt())
                    .catch {
                        characterState.value =
                            CharacterApiState.error(it.message.toString())
                    }
                    .collect {
                        characterState.value = CharacterApiState.success(it.data)
                    }
            }
        }

    private fun getEpisode() {
            episodeState.value = CharacterApiState.loading()
            viewModelScope.launch {
                repository.getEpisode(characterId.toInt())
                    .catch {
                        episodeState.value =
                            CharacterApiState.error(it.message.toString())
                    }
                    .collect {
                        episodeState.value = CharacterApiState.success(it.data)
                    }
            }
        }

    private fun getFavCharacter() {
        getFavCharacter = favCharacterDAO.getFavCharacter(characterId)
    }

    fun insert(favCha: FavCharacter){
        CoroutineScope(Dispatchers.IO).launch {
            favCharacterDAO.insertFavCharacter(favCha)
        }
    }

    fun update(id:String, isFav: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            favCharacterDAO.updateCharacter(id,isFav)
        }
    }
}
