package com.example.proyectofinal_rubenlozano.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_rubenlozano.R
import com.example.proyectofinal_rubenlozano.models.LastFm

class LastFmAdapter(
    public var lista: MutableList<LastFm>,
    private val onDetalleClik: (LastFm)->Unit,
): RecyclerView.Adapter<LastFmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastFmViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.lastfm_layout, parent, false)
        return LastFmViewHolder(v)
    }

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: LastFmViewHolder, position: Int) {
        val musica=lista[position]
        holder.render(musica, onDetalleClik)
    }
}