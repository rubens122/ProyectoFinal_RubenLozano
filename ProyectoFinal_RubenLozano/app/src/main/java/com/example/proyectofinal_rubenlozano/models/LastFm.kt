package com.example.proyectofinal_rubenlozano.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LastFm(
    @SerializedName("name") val nombre: String,
    @SerializedName("playcount") val reproducciones: Int,
    @SerializedName("listeners") val oyentes: Int,
    @SerializedName("url") val url: String,
    @SerializedName("imagen") val imagenes: List<Image>
) : Serializable

data class Image(
    @SerializedName("#text") val url: String,
    val size: String
)
data class ListaMusica(
    @SerializedName("results") val listaMusica: MutableList<LastFm>)

