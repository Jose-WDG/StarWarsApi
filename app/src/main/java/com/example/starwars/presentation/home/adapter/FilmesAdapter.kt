package com.example.starwars.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.infrastructure.Filme

class FilmesAdapter(var filmes: List<Filme>) : RecyclerView.Adapter<FilmesAdapter.FilmesAdaoterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesAdaoterViewHolder {
        val inflate = LayoutInflater.from(parent.context)
                .inflate(R.layout.filmes_item_list, parent, false)
        return FilmesAdaoterViewHolder(inflate)
    }

    fun seTData(filmes: List<Filme>){
        this.filmes = filmes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    override fun onBindViewHolder(holder: FilmesAdaoterViewHolder, position: Int) {
        holder.titulo.text = filmes[position].title
        holder.dataLacamento.text = filmes[position].releaseDate
    }

    class FilmesAdaoterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titulo = itemView.findViewById<TextView>(R.id.filme_titulo)
        var dataLacamento = itemView.findViewById<TextView>(R.id.filme_data_lacamento)
    }
}