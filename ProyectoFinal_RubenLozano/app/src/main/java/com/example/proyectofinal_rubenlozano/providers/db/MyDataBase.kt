package com.example.proyectofinal_rubenlozano.providers.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyectofinal_rubenlozano.Aplicacion

class MyDatabase() : SQLiteOpenHelper(Aplicacion.appContext, Aplicacion.DB, null, Aplicacion.VERSION) {

    private val qUsuarios = """
        create table ${Aplicacion.TABLA_USUARIOS} (
            id integer primary key autoincrement,
            email text not null unique,
            password text not null
        );
    """

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(qUsuarios)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            val dropUsuarios = "drop table if exists ${Aplicacion.TABLA_USUARIOS};"
            db?.execSQL(dropUsuarios)
            onCreate(db)
        }
    }
}
