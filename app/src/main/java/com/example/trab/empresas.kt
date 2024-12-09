package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.dataclass.ListEmpresas

private lateinit var myListEmpresas: ArrayList<ListEmpresas>

class empresas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresas)

        // Recuperar os IDs passados pelo Intent
        val cursoId = intent.getIntExtra("CURSO_ID", -1)
        val escolaId = intent.getIntExtra("ESCOLA_ID", -1)

        // Mostrar os IDs no Toast
        Toast.makeText(
            this,
            "Curso ID: $cursoId, Escola ID: $escolaId",
            Toast.LENGTH_SHORT
        ).show()

        // Inicialize a lista antes de usá-la
        myListEmpresas = ArrayList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_empresas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycler_view: RecyclerView = findViewById(R.id.recycler_view)
        //recycler_view.adapter = EmpresasAdapter(myListEmpresas)
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    fun inicio(view: View) {
        val intent = Intent(applicationContext, Cursos::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
