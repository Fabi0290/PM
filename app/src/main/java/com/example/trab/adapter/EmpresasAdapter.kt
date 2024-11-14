package com.example.trab
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.R
import com.example.trab.dataclass.ListEmpresas
import java.util.ArrayList

class NotaAdapter(private val dataSet: ArrayList<ListEmpresas>) :
    RecyclerView.Adapter<NotaAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView
        val endereco: TextView
        val cidade: TextView
        val email : TextView
        val telefone : TextView


        init {
            // Define click listener for the ViewHolder's View
            name = view.findViewById(R.id.name)
            endereco = view.findViewById(R.id.endereco)
            cidade= view.findViewById(R.id.cidade)
            email= view.findViewById(R.id.email)
            telefone= view.findViewById(R.id.telefone)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.linha, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = dataSet[position].name
        viewHolder.endereco.text = dataSet[position].endereco
        viewHolder.cidade.text = dataSet[position].cidade
        viewHolder.email.text = dataSet[position].email
        viewHolder.telefone.text = dataSet[position].telefone

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
