package com.example.rickandmorty.model

data class RickyAndMortList(val info: info, val results: List<Character>)
data class Character(
    val id: String? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null,
    val location: Location? = null
)

data class Location(
    val name: String? = null,
    val url: String? = null
)

data class info(val count: Int?, val pages: String?, val next: String?, val prev: String?)

/*
* data class Character(
    val id: String? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val image: String? = null
)*/