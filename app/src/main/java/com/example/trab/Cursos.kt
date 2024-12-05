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
import com.example.trab.adapter.EscolasAdapter
import com.example.trab.api.Curso
import com.example.trab.api.EndPoints
import com.example.trab.api.Escolas
import com.example.trab.api.ServiceBuilder
import com.example.trab.dataclass.ListCursos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Criar lista de cursos
//private lateinit var myListCursos:ArrayList<ListCursos>

class Cursos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_cursos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getEscolas()

        call.enqueue(object : Callback<List<Escolas>> {
            override fun onResponse(call: Call<List<Escolas>>, response: Response<List<Escolas>>) {
                if (response.isSuccessful){
                    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view2)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Cursos)
                        adapter = EscolasAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Escolas>>, t: Throwable) {
                Toast.makeText(this@Cursos, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })




    //ARRAY DE RECICLE VIEW PARA aos cursos
        //myListCursos = ArrayList<ListCursos>()
        //myListCursos.add(ListCursos("Engenharia de Redes e Sistemas de Computador", "tecnologias", "14.5", "João Paulo"))


        val recycler_view: RecyclerView = findViewById(R.id.recycler_view2)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    }
