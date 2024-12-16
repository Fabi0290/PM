package com.example.trab.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.api.Curso
import com.example.trab.empresas

class CursoAdapter(private val cursos: List<Curso>, private val escolaId: Int) :
    RecyclerView.Adapter<CursoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeCurso: TextView = view.findViewById(R.id.cursos_name)
        val siglaCurso: TextView = view.findViewById(R.id.cursos_desc)
        val cordCurso: TextView = view.findViewById(R.id.cordenador)
        val numAluno: TextView = view.findViewById(R.id.num_alunos)
        val setor: TextView = view.findViewById(R.id.setor)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
        val btnEmpresas: Button = view.findViewById(R.id.btn_empresas)
        val itemContainer: RelativeLayout = view.findViewById(R.id.linha2)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.linha2, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val curso = cursos[position]

        viewHolder.nomeCurso.text = curso.nome_curso
        viewHolder.siglaCurso.text = curso.sigla
        viewHolder.setor.text = "Setor: " + curso.setor
        viewHolder.cordCurso.text = "Cord: " + curso.coordenador
        viewHolder.numAluno.text = "Nº Alunos: " + curso.num_alunos.toString()

        // Alternar visibilidade do container ao clicar na área inteira
        viewHolder.itemContainer.setOnClickListener {
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

            intent.putExtra("CURSO_ID", curso.id_curso)
            intent.putExtra("ESCOLA_ID", escolaId)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = cursos.size
}