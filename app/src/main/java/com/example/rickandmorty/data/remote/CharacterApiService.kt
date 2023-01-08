package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.data.model.RickyAndMortList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    @GET("character")
    suspend fun getAllCharacters(@Query("name") name: String?,
                                 @Query("status") status: String?,
                                 @Query("page") page: Int?): RickyAndMortList

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Character

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode
}


