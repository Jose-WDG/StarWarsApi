package com.example.starwars.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.infrastructure.Filme
import java.util.*
import kotlin.collections.ArrayList

class FilmesAdapter(var filmes: List<Filme>) : RecyclerView.Adapter<FilmesAdapter.FilmesAdaoterViewHolder>(){
    private lateinit var context: Context
    private var listener: HolderListener<Filme>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesAdaoterViewHolder {
        val inflate = LayoutInflater.from(parent.context)
                .inflate(R.layout.filmes_item_list, parent, false)
        context = parent.context
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
        listener?.let {l ->
            holder.itemView.setOnClickListener {
                l.listenerImpl(position,filmes)
            }
        }
    }

    fun setLisstener(listener: HolderListener<Filme>){
        this.listener = listener
    }

    class FilmesAdaoterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titulo = itemView.findViewById<TextView>(R.id.filme_titulo)
        var dataLacamento = itemView.findViewById<TextView>(R.id.filme_data_lacamento)
    }

}
