package com.example.rickandmorty.data.remote


object CharacterApi {
    val RETROFIT_SERVICE: CharacterApiService by lazy {
        CharacterService.retrofit.create(CharacterApiService::class.java)
    }
}

