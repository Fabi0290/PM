package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.api.Empresa
import com.example.trab.api.ServiceBuilder
import com.example.trab.api.EndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class empresas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresas)

        // Recuperar os IDs passados pelo Intent
        val cursoId = intent.getIntExtra("CURSO_ID", -1)
        val escolaId = intent.getIntExtra("ESCOLA_ID", -1)

        if (cursoId == -1 || escolaId == -1) {
            Toast.makeText(this, "Erro ao carregar IDs", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Configurar a RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fazer a requisição para buscar empresas
        val apiService = ServiceBuilder.buildService(EndPoints::class.java)
        fetchEmpresas(escolaId, cursoId, apiService, recyclerView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_empresas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchEmpresas(escolaId: Int, cursoId: Int, request: EndPoints, recyclerView: RecyclerView) {
        request.getEmpresas(escolaId, cursoId).enqueue(object : Callback<List<Empresa>> {
            override fun onResponse(call: Call<List<Empresa>>, response: Response<List<Empresa>>) {
                if (response.isSuccessful) {
                    val empresas = response.body() ?: emptyList()

                    // Configurar o Adapter com as empresas
                    recyclerView.adapter = EmpresasAdapter(ArrayList(empresas))
                } else {
                    Toast.makeText(this@empresas, "Erro ao buscar empresas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Empresa>>, t: Throwable) {
                Toast.makeText(this@empresas, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun inicio(view: View) {
        val intent = Intent(applicationContext, Cursos::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
