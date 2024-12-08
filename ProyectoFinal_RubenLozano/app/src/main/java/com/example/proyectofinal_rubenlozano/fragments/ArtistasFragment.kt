package com.example.proyectofinal_rubenlozano.fragments

import com.example.proyectofinal_rubenlozano.sharedpreferences.Preferencias
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectofinal_rubenlozano.DetalleArtistasActivity
import com.example.proyectofinal_rubenlozano.R
import com.example.proyectofinal_rubenlozano.adapters.LastFmAdapter
import com.example.proyectofinal_rubenlozano.databinding.FragmentArtistasBinding
import com.example.proyectofinal_rubenlozano.models.LastFm
import com.example.proyectofinal_rubenlozano.providers.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistasFragment : Fragment() {

    private lateinit var binding: FragmentArtistasBinding
    private val listaArtistas = mutableListOf<LastFm>()
    private val adapter = LastFmAdapter(mutableListOf()) { artista ->
        irDetalle(artista)
    }
    private var apiKey = ""
    private lateinit var preferencias: Preferencias

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiKey = getString(R.string.last_fm_api_key)
        preferencias = Preferencias(requireContext())
        setRecycler()
        configurarSeekBar()
        traerDatos()
        mostrarUltimoArtista()
    }

    private fun mostrarUltimoArtista() {
        val ultimoArtista = preferencias.getUltimoArtista()
        Toast.makeText(requireContext(), "Último artista visto: $ultimoArtista", Toast.LENGTH_SHORT).show()
    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recView.layoutManager = layoutManager
        binding.recView.adapter = adapter
    }

    private fun configurarSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val artistasAMostrar = (progress + 1) * 2
                binding.tvArtistasAMostrar.text = "Artistas a mostrar: $artistasAMostrar"
                actualizarListaArtistas(artistasAMostrar)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun actualizarListaArtistas(artistasAMostrar: Int) {
        val subLista = listaArtistas.take(artistasAMostrar)
        adapter.lista.clear()
        adapter.lista.addAll(subLista)
        adapter.notifyDataSetChanged()
    }

    private fun traerDatos() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiClient.traer(apiKey)
                if (response.isSuccessful) {
                    val artistas = response.body()?.artists?.listaMusica ?: emptyList()

                    withContext(Dispatchers.Main) {
                        listaArtistas.clear()
                        listaArtistas.addAll(artistas)
                        actualizarListaArtistas((binding.seekBar.progress + 1) * 2)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error al cargar artistas", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun irDetalle(artista: LastFm) {
        preferencias.setUltimoArtista(artista.nombre)
        val intent = Intent(requireContext(), DetalleArtistasActivity::class.java)
        intent.putExtra("ARTISTA", artista)
        startActivity(intent)
    }
}
