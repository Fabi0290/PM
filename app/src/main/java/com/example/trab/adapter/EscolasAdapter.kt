package com.example.trab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.api.Escolas

class EscolasAdapter(private val escolas: List<Escolas>) :
    RecyclerView.Adapter<EscolasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeCursos: TextView = view.findViewById(R.id.cursos_name)
        val sigla: TextView = view.findViewById(R.id.cursos_desc)
        val empresas: TextView = view.findViewById(R.id.cursos_empresas)
        val btnMais: Button = view.findViewById(R.id.btn_mais)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha2, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val escola = escolas[position]

        // Exibir apenas o nome do primeiro curso no TextView
        if (escola.cursos.isNotEmpty()) {

            viewHolder.nomeCursos.text = escola.cursos[0].nome_curso
            viewHolder.sigla.text = escola.cursos[0].sigla
            viewHolder.empresas.text = escola.cursos[0].empresas[0].nome
        } else {
            viewHolder.nomeCursos.text = "Sem cursos disponíveis"
        }

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

    override fun getItemCount() = escolas.size
}
