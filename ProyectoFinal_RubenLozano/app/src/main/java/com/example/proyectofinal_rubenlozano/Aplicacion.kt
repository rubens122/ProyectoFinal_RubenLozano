package com.example.proyectofinal_rubenlozano

import android.app.Application
import android.content.Context
import com.example.proyectofinal_rubenlozano.providers.db.MyDatabase

class Aplicacion : Application() {
    companion object {
        const val VERSION = 2
        const val DB = "Base_1"
        const val TABLA_USUARIOS = "usuarios" // Nueva tabla
        lateinit var appContext: Context
        lateinit var llave: MyDatabase
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        llave = MyDatabase()
    }
}
