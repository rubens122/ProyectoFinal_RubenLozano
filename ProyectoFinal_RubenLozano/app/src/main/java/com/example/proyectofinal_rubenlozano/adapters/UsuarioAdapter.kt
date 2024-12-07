package com.example.proyectofinal_rubenlozano.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.R
import com.example.proyectofinal_rubenlozano.models.UsuarioModel

class UsuarioAdapter(
    var listaUsuarios: MutableList<UsuarioModel>,
    private val borrarUsuario: (Int) -> Unit,  // Para borrar un usuario
    private val updateUsuario: (UsuarioModel) -> Unit  // Para actualizar un usuario
) : RecyclerView.Adapter<UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.usuario, parent, false)
        return UsuarioViewHolder(v)
    }

    override fun getItemCount(): Int = listaUsuarios.size

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.render(listaUsuarios[position], borrarUsuario, updateUsuario)
    }

}
