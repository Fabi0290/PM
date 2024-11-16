package com.example.trab
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.dataclass.ListEmpresas
import java.util.ArrayList

class NotaAdapter(private val dataSet: ArrayList<ListEmpresas>) :
    RecyclerView.Adapter<NotaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val cidade: TextView = view.findViewById(R.id.cidade)
        val btnMais: Button = view.findViewById(R.id.btn_mais)
        val infoContainer: LinearLayout = view.findViewById(R.id.info_container)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val empresa = dataSet[position]

        // Preencher os dados
        viewHolder.name.text = empresa.name
        viewHolder.cidade.text = empresa.cidade

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

