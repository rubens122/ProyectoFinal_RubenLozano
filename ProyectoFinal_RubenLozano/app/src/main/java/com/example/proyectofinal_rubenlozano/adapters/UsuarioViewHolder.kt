package com.example.proyectofinal_rubenlozano.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.databinding.UsuarioBinding
import com.example.proyectofinal_rubenlozano.models.UsuarioModel

class UsuarioViewHolder(v: View): RecyclerView.ViewHolder(v){
    val binding  = UsuarioBinding.bind(v)
    fun render(usuario: UsuarioModel, borrarUsuario: (Int) -> Unit, updateUsuario: (UsuarioModel) -> Unit) {
        // Asignar los valores de los usuarios a las vistas con ViewBinding
        binding.tvEmail.text = usuario.email
        binding.tvPassword.text = usuario.password

        // Configurar el botón de borrar
        binding.btnDelete.setOnClickListener {
            borrarUsuario(adapterPosition)
        }

        // Configurar el botón de actualizar
        binding.btnUpdate.setOnClickListener {
            updateUsuario(usuario)
        }
    }
}