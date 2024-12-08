package com.example.proyectofinal_rubenlozano.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.databinding.LastfmLayoutBinding
import com.example.proyectofinal_rubenlozano.models.LastFm
import com.squareup.picasso.Picasso

class LastFmViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val binding = LastfmLayoutBinding.bind(v)
    fun render(musica: LastFm, onDetalleClick: (LastFm) -> Unit) {
        val imagenMediana = musica.getImageUrl("medium")
        binding.tvNombre.text = musica.nombre
        Picasso.get().load(imagenMediana).into(binding.ivImagen)
        itemView.setOnClickListener {
            onDetalleClick(musica)
        }
    }
}