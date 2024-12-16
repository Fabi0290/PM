package com.example.trab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.api.Empresa
import com.example.trab.EmpresaDetailsActivity

class EmpresasAdapter(private val dataSet: ArrayList<Empresa>) :
    RecyclerView.Adapter<EmpresasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val cidade: TextView = view.findViewById(R.id.cidade)
        val descricao: TextView = view.findViewById(R.id.empresa_descricao)
        val numAlunos: TextView = view.findViewById(R.id.empresa_num_alunos)
        val numVagas: TextView = view.findViewById(R.id.empresa_num_vagas)
        //val Setor: TextView = view.findViewById(R.id.setor)
        //val Taxa: TextView = view.findViewById(R.id.taxa_ac)
        //val Ano: TextView = view.findViewById(R.id.ano)
        //val Duracao: TextView = view.findViewById(R.id.duracao)

        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
        val container: RelativeLayout = view.findViewById(R.id.linha)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val empresa = dataSet[position]

        // Configurar os valores para cada item
        viewHolder.name.text = empresa.nome
        viewHolder.cidade.text = empresa.cidade
        viewHolder.descricao.text = empresa.descricao
       // viewHolder.Setor.text = empresa.setor
       // viewHolder.Taxa.text = empresa.taxa_aceitação.toString()
        //viewHolder.Ano.text = empresa.ano_criacao.toString()
        //viewHolder.Duracao.text = empresa.duracao_estagios

        viewHolder.numAlunos.text = "Alunos: ${empresa.num_alunos}"
        viewHolder.numVagas.text = "Vagas: ${empresa.num_vagas}"

        // Alternar visibilidade do container ao clicar na área
        viewHolder.container.setOnClickListener {
            if (viewHolder.infoContainer.visibility == View.GONE) {
                viewHolder.infoContainer.visibility = View.VISIBLE
            } else {
                viewHolder.infoContainer.visibility = View.GONE
            }
        }

        // Adicionar clique para navegar para a atividade de detalhes
        viewHolder.itemView.setOnClickListener {
            val context = viewHolder.itemView.context
            val intent = Intent(context, EmpresaDetailsActivity::class.java).apply {
                putExtra("nome", empresa.nome)
                putExtra("descricao", empresa.descricao)
                putExtra("cidade", empresa.cidade)
                putExtra("num_alunos", empresa.num_alunos)
                putExtra("num_vagas", empresa.num_vagas)
                putExtra("setor", empresa.setor)
                putExtra("taxa", empresa.taxa_aceitacao)
                putExtra("ano_criacao", empresa.ano_criacao)
                putExtra("duracao_estagio", empresa.duracao_estagio)

            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}
