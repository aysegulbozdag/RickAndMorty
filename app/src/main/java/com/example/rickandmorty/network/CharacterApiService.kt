package com.example.rickandmorty.network

import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.RickyAndMortList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    @GET("character")
   suspend fun getAllCharacters(@Query("page") page: Int) : RickyAndMortList

   @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id")id: Int) : Character

    suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    )
}


