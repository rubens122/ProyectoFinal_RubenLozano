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

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsuarioAdapter
    private var listaUsuarios = mutableListOf<UsuarioModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Configurar el adaptador y asignarlo al RecyclerView
        adapter = UsuarioAdapter(
            listaUsuarios,
            ::borrarUsuario, // Pasar el método como referencia
            ::actualizarUsuario // Pasar el método como referencia
        )
        recyclerView.adapter = adapter

        // Traer los usuarios al inicializar el adaptador
        traerUsuarios()

        return view
    }

    private fun traerUsuarios() {
        // Obtener lista de usuarios desde la base de datos
        listaUsuarios.clear()
        listaUsuarios.addAll(CrudUsuarios().read())

        // Notificar al adaptador de los cambios en los datos
        adapter.notifyDataSetChanged()
    }

    private fun borrarUsuario(position: Int) {
        val usuarioAEliminar = listaUsuarios[position]
        CrudUsuarios().delete(usuarioAEliminar.id) // Eliminar de la base de datos
        traerUsuarios() // Refrescar lista
    }

    private fun actualizarUsuario(usuario: UsuarioModel) {
        val intent = Intent(requireContext(), ActualizarUsuario::class.java).apply {
            putExtra("USUARIO", usuario) // Asegúrate de que UsuarioModel implemente Parcelable o Serializable
        }
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        traerUsuarios() // Refrescar lista al volver al fragmento
    }
}
