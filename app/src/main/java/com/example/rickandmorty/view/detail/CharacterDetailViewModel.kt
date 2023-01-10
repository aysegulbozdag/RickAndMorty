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
import com.example.rickandmorty.data.remote.CharacterApi
import com.example.rickandmorty.data.remote.State
import com.example.rickandmorty.data.remote.Status
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.usecase.GetCharacterUseCase
import com.example.rickandmorty.domain.usecase.GetEpisodeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val context: Context, private val characterId: String) : ViewModel() {

    private val repositoryGetCharacter =
        GetCharacterUseCase(CharacterRepositoryImpl(CharacterApi.RETROFIT_SERVICE))
    private val repositoryGetEpisode =
        GetEpisodeUseCase(CharacterRepositoryImpl(CharacterApi.RETROFIT_SERVICE))
    val characterState = MutableStateFlow(State(Status.LOADING, Character(), ""))
    val episodeState = MutableStateFlow(State(Status.LOADING, Episode(), ""))
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
            viewModelScope.launch {
                repositoryGetCharacter.invoke(characterId.toInt())
                    .catch {
                        characterState.value =
                            State.error(it.message.toString())
                    }
                    .onEach {
                        characterState.value = State.success(it.data)
                    }
            }
        }

    private fun getEpisode() {
        viewModelScope.launch {
            repositoryGetEpisode.invoke(characterId.toInt())
                .catch {
                    episodeState.value =
                        State.error(it.message.toString())
                }
                .collect {
                    episodeState.value = State.success(it.data)
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
