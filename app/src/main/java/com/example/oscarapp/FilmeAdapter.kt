package com.example.oscarapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FilmeAdapter(private val filmes: List<Filme>, private val onClick: (Filme) -> Unit) :
    RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    class FilmeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.tvNome)
        val genero: TextView = view.findViewById(R.id.tvGenero)
        val poster: ImageView = view.findViewById(R.id.ivPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = filmes[position]
        holder.nome.text = filme.nome
        holder.genero.text = filme.genero
        Picasso.get().load(filme.posterUrl).into(holder.poster) // Carregar imagem com Picasso
        holder.itemView.setOnClickListener { onClick(filme) }
    }

    override fun getItemCount() = filmes.size
}
