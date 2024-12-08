package com.example.proyectofinal_rubenlozano.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LastFm(
    @SerializedName("name") val nombre: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val imagenes: List<Image>
) : Serializable {
    fun getImageUrl(size: String = "medium"): String? {
        return imagenes.find { it.size == size }?.url
    }
}

data class Image(
    @SerializedName("#text") val url: String,
    val size: String
) : Serializable

data class ListaMusica(
    @SerializedName("artists") val artists: Artists
)

data class Artists(
    @SerializedName("artist") val listaMusica: MutableList<LastFm>
)

