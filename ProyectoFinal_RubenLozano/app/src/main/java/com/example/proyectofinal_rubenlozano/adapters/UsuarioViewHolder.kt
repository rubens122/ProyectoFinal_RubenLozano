package com.example.proyectofinal_rubenlozano.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.databinding.UsuarioBinding
import com.example.proyectofinal_rubenlozano.models.UsuarioModel

class UsuarioViewHolder(v: View): RecyclerView.ViewHolder(v){
    val binding  = UsuarioBinding.bind(v)
    fun render(usuario: UsuarioModel, borrarUsuario: (Int) -> Unit, updateUsuario: (UsuarioModel) -> Unit) {
        binding.tvEmail.text = usuario.email
        binding.tvPassword.text = usuario.password

        binding.btnBorrar.setOnClickListener {
            borrarUsuario(adapterPosition)
        }

        binding.btnActualizar.setOnClickListener {
            updateUsuario(usuario)
        }
    }
}