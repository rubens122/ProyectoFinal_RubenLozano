package com.example.proyectofinal_rubenlozano.models

import java.io.Serializable

data class UsuarioModel(
    val id: Int,
    var email: String,
    var password: String
) : Serializable
