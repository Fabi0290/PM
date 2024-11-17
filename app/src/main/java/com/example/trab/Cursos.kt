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
import com.example.trab.adapter.CursosAdapter
import com.example.trab.dataclass.ListCursos

//Criar lista de cursos
private lateinit var myListCursos:ArrayList<ListCursos>

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


        //ARRAY DE RECICLE VIEW PARA aos cursos
        myListCursos = ArrayList<ListCursos>()
        myListCursos.add(ListCursos("Engenharia de Redes e Sistemas de Computador", "tecnologias", "14.5", "João Paulo"))
        myListCursos.add(ListCursos("Engenharia Informática", "tecnologias", "15.0", "Ana Silva"))
        myListCursos.add(ListCursos("Gestão", "ciências empresariais", "13.8", "Carlos Santos"))
        myListCursos.add(ListCursos("Contabilidade", "ciências empresariais", "13.5", "Mariana Costa"))
        myListCursos.add(ListCursos("Design de Produto", "artes e design", "14.7", "Rui Alves"))
        myListCursos.add(ListCursos("Marketing e Comunicação Empresarial", "ciências empresariais", "13.6", "Sara Ferreira"))
        myListCursos.add(ListCursos("Biotecnologia", "ciências biológicas", "14.8", "João Mendes"))
        myListCursos.add(ListCursos("Engenharia Alimentar", "engenharias", "14.3", "Sofia Oliveira"))
        myListCursos.add(ListCursos("Turismo", "humanidades", "13.9", "Pedro Costa"))
        myListCursos.add(ListCursos("Educação Básica", "ciências da educação", "14.1", "Ana Matos"))
        myListCursos.add(ListCursos("Enfermagem", "saúde", "15.2", "Cátia Pereira"))
        myListCursos.add(ListCursos("Fisioterapia", "saúde", "14.9", "Tiago Gomes"))
        myListCursos.add(ListCursos("Engenharia Mecânica", "engenharias", "14.4", "Miguel Sousa"))
        myListCursos.add(ListCursos("Engenharia Civil", "engenharias", "14.2", "Ricardo Almeida"))
        myListCursos.add(ListCursos("Design Gráfico e Multimédia", "artes e design", "15.0", "Patrícia Martins"))
        myListCursos.add(ListCursos("Engenharia do Ambiente", "engenharias", "14.6", "Bruno Carvalho"))
        myListCursos.add(ListCursos("Ciências Biomédicas", "ciências biológicas", "14.9", "Leonor Rodrigues"))
        myListCursos.add(ListCursos("Gestão Hoteleira", "humanidades", "13.7", "Helena Rocha"))
        myListCursos.add(ListCursos("Animação Sociocultural", "ciências sociais", "13.6", "Eduardo Lima"))
        myListCursos.add(ListCursos("Desporto e Lazer", "ciências do desporto", "14.0", "Marcos Silva"))


        val recycler_view: RecyclerView = findViewById(R.id.recycler_view2)
        recycler_view.adapter = CursosAdapter(myListCursos)
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
    }
