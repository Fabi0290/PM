package com.example.trab
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.api.Empresa
import com.example.trab.dataclass.ListEmpresas
import java.util.ArrayList

class EmpresasAdapter(private val dataSet: ArrayList<Empresa>) :
    RecyclerView.Adapter<EmpresasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val cidade: TextView = view.findViewById(R.id.cidade)
        val descricao: TextView = view.findViewById(R.id.empresa_descricao)
        val numAlunos: TextView = view.findViewById(R.id.empresa_num_alunos)
        val numVagas: TextView = view.findViewById(R.id.empresa_num_vagas)
        val btnMais: Button = view.findViewById(R.id.btn_mais)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.linha, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val empresa = dataSet[position]

        viewHolder.name.text = empresa.nome
        viewHolder.cidade.text = empresa.cidade
        viewHolder.descricao.text = empresa.descricao
        viewHolder.numAlunos.text = empresa.num_alunos.toString()
        viewHolder.numVagas.text = empresa.num_vagas.toString()

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


