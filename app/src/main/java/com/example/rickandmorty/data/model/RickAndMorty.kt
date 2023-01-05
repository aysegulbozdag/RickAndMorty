package com.example.rickandmorty.data.model

data class RickyAndMortList(val info: Info, val results: List<Character>)
data class Character(
    val id: String? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null,
    val location: Location? = null,
    val origin: Origin? = null,
    val gender: String? = null,
    val episode: ArrayList<String>? = null
)

data class Location(val name: String? = null, val url: String? = null)

data class Origin(
    val name: String? = null,
    val url: String? = null
)

data class Episode(
    val id: Int? = null,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null
)

data class Info(val count: Int?, val pages: String?, val next: String?, val prev: String?)