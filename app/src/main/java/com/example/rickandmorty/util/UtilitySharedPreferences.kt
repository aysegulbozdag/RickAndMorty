package com.example.rickandmorty.util

import android.app.Activity
import android.content.Context

object UtilitySharedPreferences {
    const val getPackageName = "RickAndMorty"
    fun putSPBoolean(context: Context, key: String, value: Boolean) {
        val preferences = context.getSharedPreferences(getPackageName, Activity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getSPBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        val preferences = context.getSharedPreferences(getPackageName, Activity.MODE_PRIVATE)
        return preferences.getBoolean(key, defaultValue)
    }

}