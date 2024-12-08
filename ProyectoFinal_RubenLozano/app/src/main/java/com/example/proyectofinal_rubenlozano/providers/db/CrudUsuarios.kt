package com.example.proyectofinal_rubenlozano.providers.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.proyectofinal_rubenlozano.Aplicacion
import com.example.proyectofinal_rubenlozano.models.UsuarioModel

class CrudUsuarios {
    fun create(usuario: UsuarioModel): Long {
        val db = Aplicacion.llave.writableDatabase
        return try {
            db.insertWithOnConflict(
                Aplicacion.TABLA_USUARIOS,
                null,
                usuario.toContentValues(),
                SQLiteDatabase.CONFLICT_IGNORE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            -1L
        } finally {
            db.close()
        }
    }
    fun verificarUsuarioExistente(email: String): Boolean {
        val con = Aplicacion.llave.readableDatabase
        val cursor: Cursor = con.rawQuery(
            "SELECT * FROM usuarios WHERE email = ?",
            arrayOf(email)
        )
        val existe = cursor.moveToFirst()
        cursor.close()
        con.close()
        return existe
    }

    fun read(): MutableList<UsuarioModel> {
        val lista = mutableListOf<UsuarioModel>()
        val db = Aplicacion.llave.readableDatabase
        try {
            val cursor = db.query(
                Aplicacion.TABLA_USUARIOS,
                arrayOf("id", "email", "password"),
                null,
                null,
                null,
                null,
                null
            )
            while (cursor.moveToNext()) {
                val usuario = UsuarioModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                )
                lista.add(usuario)
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
        return lista
    }

    fun update(usuario: UsuarioModel): Boolean {
        val db = Aplicacion.llave.writableDatabase
        val values = usuario.toContentValues()
        val filasActualizadas = db.update(
            Aplicacion.TABLA_USUARIOS,
            values,
            "id=?",
            arrayOf(usuario.id.toString())
        )
        db.close()
        return filasActualizadas > 0
    }

    fun delete(id: Int): Boolean {
        val db = Aplicacion.llave.writableDatabase
        val filasEliminadas = db.delete(Aplicacion.TABLA_USUARIOS, "id=?", arrayOf(id.toString()))
        db.close()
        return filasEliminadas > 0
    }

    private fun UsuarioModel.toContentValues(): ContentValues {
        return ContentValues().apply {
            put("email", email)
            put("password", password)
        }
    }
}