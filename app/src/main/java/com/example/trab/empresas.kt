package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.dataclass.ListEmpresas

private lateinit var myListEmpresas:ArrayList<ListEmpresas>
class empresas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_empresas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ARRAY DE RECICLE VIEW PARA AS EMPRESAS
        myListEmpresas = ArrayList<ListEmpresas>()

        myListEmpresas.add(ListEmpresas("Borgwarner","avenida 123","Viana do castelo","borg@ipvc.pt","900000000"))
        myListEmpresas.add(ListEmpresas("celpower","avenida 1212","Viana do castelo","pw@ipvc.pt","900000000"))
        myListEmpresas.add(ListEmpresas("porsche","avenida 23233","Viana do castelo","ps@ipvc.pt","900000000"))

        val recycler_view: RecyclerView = findViewById(R.id.recycler_view)
        recycler_view.adapter = NotaAdapter(myListEmpresas)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}