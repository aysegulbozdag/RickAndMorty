package com.example.rickandmorty.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

class NetworkModule {

    @Provides
    @Singleton
    fun getGson() :Gson = GsonBuilder().serializeNulls().setLenient().create()

    @Provides
    @Singleton
    fun getInterceptor() : Interceptor = Interceptor{
        val request = it.request().newBuilder()
        val actualRequest  = request.build()
        it.proceed(actualRequest)
    }
}