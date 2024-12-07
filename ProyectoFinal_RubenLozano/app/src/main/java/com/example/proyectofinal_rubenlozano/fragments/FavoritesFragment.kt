package com.example.proyectofinal_rubenlozano.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectofinal_rubenlozano.R
import com.example.proyectofinal_rubenlozano.adapters.LastFmAdapter
import com.example.proyectofinal_rubenlozano.databinding.FragmentFavoritesBinding
import com.example.proyectofinal_rubenlozano.models.LastFm
import com.example.proyectofinal_rubenlozano.providers.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val listaArtistas = mutableListOf<LastFm>()
    private val adapter = LastFmAdapter(listaArtistas) { artista ->
        irDetalle(artista)
    }
    private var apiKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiKey = getString(R.string.last_fm_api_key)
        setRecycler()
        traerDatos()
    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recView.layoutManager = layoutManager
        binding.recView.adapter = adapter
    }

    private fun traerDatos() {
        lifecycleScope.launch(Dispatchers.IO) {
            val response = ApiClient.apiClient.traer(apiKey)
            val artistas = response.body()?.listaMusica ?: emptyList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    listaArtistas.clear()
                    listaArtistas.addAll(artistas)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error al cargar artistas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun irDetalle(artista: LastFm) {
        // Implementa la navegación a un detalle de artista si es necesario
        Toast.makeText(requireContext(), "Artista: ${artista.nombre}", Toast.LENGTH_SHORT).show()
    }
}
