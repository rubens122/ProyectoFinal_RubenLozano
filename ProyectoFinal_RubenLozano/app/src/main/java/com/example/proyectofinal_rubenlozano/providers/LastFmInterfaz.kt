package com.example.proyectofinal_rubenlozano.providers

import com.example.proyectofinal_rubenlozano.models.ListaMusica
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmInterfaz {
    @GET("/2.0/?method=chart.gettopartists&format=json")
    suspend fun traer(@Query("api_key") key: String): Response<ListaMusica>
}