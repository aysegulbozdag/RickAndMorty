package com.example.rickandmorty.data.network


object CharacterApi {
    val RETROFIT_SERVICE: CharacterApiService by lazy {
        CharacterService.retrofit.create(CharacterApiService::class.java)
    }
}

