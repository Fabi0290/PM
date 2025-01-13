package com.example.trab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.EmpresaDetailsActivity
import com.example.trab.R
import com.example.trab.entities.Comentarios

class COMMListAdapter(private val onItemLongClick: (Comentarios) -> Unit) : ListAdapter<Comentarios, COMMListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent, onItemLongClick)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WordViewHolder(itemView: View, private val onItemLongClick: (Comentarios) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val CommItemView: TextView = itemView.findViewById(R.id.text1)
        private val Rating: TextView = itemView.findViewById(R.id.rating)

        //private val wordItemView4: TextView = itemView.findViewById(R.id.text4)

        fun bind(item: Comentarios?) {
            CommItemView.text = item!!.texto
            Rating.text=item!!.estrelas.toString()

            // Configura o clique longo para o item
            itemView.setOnLongClickListener {
                item?.let {
                    // Passa o comentário para a função de clique longo
                    onItemLongClick(it)
                    //passa para activity
                    (itemView.context as EmpresaDetailsActivity).comentarioIdSelecionado = item.id
                }
                true // Retorna true para indicar que o evento foi consumido
            }
        }

        companion object {
            fun create(parent: ViewGroup, onItemLongClick: (Comentarios) -> Unit): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_comm, parent, false)
                return WordViewHolder(view,onItemLongClick)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Comentarios>() {
        override fun areItemsTheSame(oldItem: Comentarios, newItem: Comentarios): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comentarios, newItem: Comentarios): Boolean {
            return oldItem.texto == newItem.texto
        }
    }
}


