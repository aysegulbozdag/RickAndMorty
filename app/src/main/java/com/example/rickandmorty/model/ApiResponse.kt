package com.example.rickandmorty.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val image: String,
    val url: String,
    val origin: NameUrl,
    val location: NameUrl,
    val episode: List<String>
)

data class Results(
    @SerializedName("results") val characters: List<Character>
)


data class PagedResponse(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<Character>
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)





