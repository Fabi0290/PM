package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.adapter.CursoAdapter
import com.example.trab.api.Curso
import com.example.trab.api.EndPoints
import com.example.trab.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cursos : AppCompatActivity() {

    private val escolasIds = listOf(1, 2, 3, 4, 5, 6)  // IDs das escolas correspondentes ao Spinner
    private val escolasNomes = listOf("ESTG", "ESE", "ESS", "ESA", "ESCE", "ESDL")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cursos)

        val request = ServiceBuilder.buildService(EndPoints::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view2)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@Cursos)
        }

        // Configurar o Spinner com nomes das escolas
        val spinner: Spinner = findViewById(R.id.spinner_escolas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, escolasNomes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Adicione o listener ao Spinner para atualizar o ID da busca
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedEscolaId = escolasIds[position]
                fetchCursos(selectedEscolaId, request, recyclerView)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Opcional: Ação caso nada seja selecionado
            }
        })
    }

    private fun fetchCursos(escolaId: Int, request: EndPoints, recyclerView: RecyclerView) {
        request.getCurso(escolaId).enqueue(object : Callback<List<Curso>> {
            override fun onResponse(call: Call<List<Curso>>, response: Response<List<Curso>>) {
                if (response.isSuccessful) {
                    val cursos = response.body() ?: emptyList()

                    // Passar o ID da escola para o Adapter
                    recyclerView.adapter = CursoAdapter(cursos, escolaId)
                } else {
                    Toast.makeText(this@Cursos, "Erro ao buscar cursos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Curso>>, t: Throwable) {
                Toast.makeText(this@Cursos, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun inicio(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    fun irEmpresas(view: View) {
        // Lógica que será executada quando o botão for clicado
        val intent = Intent(applicationContext, empresas::class.java)
        startActivity(intent)
    }
}
