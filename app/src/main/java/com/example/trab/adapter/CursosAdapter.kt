package com.example.trab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.dataclass.ListCursos
import java.util.ArrayList

class CursosAdapter(private val dataSet: ArrayList<ListCursos>) :
    RecyclerView.Adapter<CursosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.curso_name)
        val area: TextView = view.findViewById(R.id.curso_area)
        val media: TextView = view.findViewById(R.id.curso_media)
        val coordenador: TextView = view.findViewById(R.id.curso_coordenador)
        val btnMais: Button = view.findViewById(R.id.btn_mais)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha2, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val curso= dataSet[position]

        // Preencher os dados
        viewHolder.name.text = curso.curso_name
        viewHolder.area.text = curso.curso_area
        viewHolder.media.text = curso.curso_media.toString()
        viewHolder.coordenador.text = curso.curso_coordenador

        // Alternar visibilidade do container
        viewHolder.btnMais.setOnClickListener {
            if (viewHolder.infoContainer.visibility == View.GONE) {
                viewHolder.infoContainer.visibility = View.VISIBLE
                viewHolder.btnMais.text = "Mostrar menos"
            } else {
                viewHolder.infoContainer.visibility = View.GONE
                viewHolder.btnMais.text = "Saber mais"
            }
        }
    }

    override fun getItemCount() = dataSet.size
}

