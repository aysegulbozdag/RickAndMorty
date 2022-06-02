package com.example.rickandmorty.network

data class CharacterApiState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): CharacterApiState<T> {
            return CharacterApiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): CharacterApiState<T> {
            return CharacterApiState(Status.ERROR, null, msg)
        }

        fun <T> loading(): CharacterApiState<T> {
            return CharacterApiState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
