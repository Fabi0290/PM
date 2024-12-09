package com.example.trab.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.api.Curso
import com.example.trab.empresas

class CursoAdapter(private val cursos: List<Curso>, private val escolaId: Int) :
    RecyclerView.Adapter<CursoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeCurso: TextView = view.findViewById(R.id.cursos_name)
        val siglaCurso: TextView = view.findViewById(R.id.cursos_desc)
        val empresasCurso: TextView = view.findViewById(R.id.cursos_empresas)
        val btnMais: Button = view.findViewById(R.id.btn_mais)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
        val btnEmpresas: Button = view.findViewById(R.id.btn_empresas) // Botão para enviar IDs
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha2, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val curso = cursos[position]

        // Exibir os detalhes do curso
        viewHolder.nomeCurso.text = curso.nome_curso
        viewHolder.siglaCurso.text = curso.sigla
        viewHolder.empresasCurso.text = curso.empresas.firstOrNull()?.nome ?: "N/D"

        // Alternar visibilidade do container
        viewHolder.btnMais.setOnClickListener {
            if (viewHolder.infoContainer.visibility == View.GONE) {
                viewHolder.infoContainer.visibility = View.VISIBLE
            } else {
                viewHolder.infoContainer.visibility = View.GONE
            }
        }

        // Clique no botão btn_empresas para abrir a nova atividade com os IDs
        viewHolder.btnEmpresas.setOnClickListener {
            val context = viewHolder.itemView.context
            val intent = Intent(context, empresas::class.java)

            // Enviar o ID do curso e da escola
            intent.putExtra("CURSO_ID", curso.id_curso)
            intent.putExtra("ESCOLA_ID", escolaId)

            // Mostrar um Toast com os IDs enviados


            context.startActivity(intent)
        }
    }

    override fun getItemCount() = cursos.size
}

