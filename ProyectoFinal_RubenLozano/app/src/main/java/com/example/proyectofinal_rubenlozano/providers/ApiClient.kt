package com.example.proyectofinal_rubenlozano.providers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit=Retrofit.Builder()
        .baseUrl("https://ws.audioscrobbler.com/2.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiClient= retrofit.create(LastFmInterfaz::class.java)
}