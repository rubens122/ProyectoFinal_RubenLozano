package com.example.proyectofinal_rubenlozano.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.ActualizarUsuario
import com.example.proyectofinal_rubenlozano.R
import com.example.proyectofinal_rubenlozano.adapters.UsuarioAdapter
import com.example.proyectofinal_rubenlozano.models.UsuarioModel
import com.example.proyectofinal_rubenlozano.providers.db.CrudUsuarios

class UsuariosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsuarioAdapter
    private var listaUsuarios = mutableListOf<UsuarioModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_usuarios, container, false)

        recyclerView = view.findViewById(R.id.rvUsuarios)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = UsuarioAdapter(
            listaUsuarios,
            ::borrarUsuario,
            ::actualizarUsuario
        )
        recyclerView.adapter = adapter
        traerUsuarios()

        return view
    }

    private fun traerUsuarios() {
        listaUsuarios.clear()
        listaUsuarios.addAll(CrudUsuarios().read())
        adapter.notifyDataSetChanged()
    }

    private fun borrarUsuario(position: Int) {
        val usuarioAEliminar = listaUsuarios[position]
        CrudUsuarios().delete(usuarioAEliminar.id)
        traerUsuarios()
    }

    private fun actualizarUsuario(usuario: UsuarioModel) {
        val intent = Intent(requireContext(), ActualizarUsuario::class.java).apply {
            putExtra("USUARIO", usuario)
        }
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        traerUsuarios()
    }
}
