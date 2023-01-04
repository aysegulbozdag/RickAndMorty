package com.example.rickandmorty.network


object CharacterApi {
    val RETROFIT_SERVICE: CharacterApiService by lazy {
        CharacterService.retrofit.create(CharacterApiService::class.java)
    }
}

