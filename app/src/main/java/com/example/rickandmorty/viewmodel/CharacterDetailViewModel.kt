package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.rickandmorty.network.*
import com.example.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.flow.catch

class CharacterDetailViewModel() : ViewModel() {

        // Create a Repository and pass the api
        // service we created in AppConfig file
        private val repository = CharacterRepository(
            CharacterApi.RETROFIT_SERVICE
        )

        val commentState = MutableStateFlow(
            CharacterApiState(
                Status.LOADING,
                Character(), ""
            )
        )

        // Function to get new Comments
        fun getNewComment(id: Int) {

            // Since Network Calls takes time,Set the
            // initial value to loading state
            commentState.value = CharacterApiState.loading()

            // ApiCalls takes some time, So it has to be
            // run and background thread. Using viewModelScope
            // to call the api
            viewModelScope.launch {

                // Collecting the data emitted
                // by the function in repository
                repository.getComment(id)
                    // If any errors occurs like 404 not found
                    // or invalid query, set the state to error
                    // State to show some info
                    // on screen
                    .catch {
                        commentState.value =
                            CharacterApiState.error(it.message.toString())
                    }
                    // If Api call is succeeded, set the State to Success
                    // and set the response data to data received from api
                    .collect {
                        commentState.value = CharacterApiState.success(it.data)
                    }
            }
        }
    }
