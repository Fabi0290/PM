package com.example.trab.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
class FavoritosAdapter(
    private val favoritos: List<String>
) : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return FavoritoViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val favorito = favoritos[position]
        holder.favoritoNome.text = favorito
    }
    override fun getItemCount() = favoritos.size
    class FavoritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val favoritoNome: TextView = itemView.findViewById(R.id.favorito_nome)
    }
}