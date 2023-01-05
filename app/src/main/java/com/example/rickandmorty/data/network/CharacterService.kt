package com.example.rickandmorty.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterService {
        companion object {
             private const val BASE_URL = "https://rickandmortyapi.com/api/"

             val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }
