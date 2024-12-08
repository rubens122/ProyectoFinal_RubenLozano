package com.example.proyectofinal_rubenlozano.sharedpreferences
import android.content.Context
import android.content.Context.MODE_PRIVATE

class Preferencias(contexto: Context) {
    private val almacenamiento = contexto.getSharedPreferences("DATOS_USUARIO", MODE_PRIVATE)

    fun setUltimoArtista(nombre: String) {
        almacenamiento.edit().putString("ULTIMO_ARTISTA", nombre).apply()
    }

    fun getUltimoArtista(): String {
        return almacenamiento.getString("ULTIMO_ARTISTA", "No hay artista guardado").toString()
    }

    fun borrarTodo() {
        almacenamiento.edit().clear().apply()
    }
}
