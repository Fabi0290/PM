package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.adapter.COMMListAdapter
import com.example.trab.databinding.ActivityEmpresaDetailsBinding
import com.example.trab.viewModel.COMMViewModel
import com.example.trab.viewModel.COMMViewModelFactory

class EmpresaDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmpresaDetailsBinding
    private lateinit var adapter: COMMListAdapter

    private val wordViewModel: COMMViewModel by viewModels {
        COMMViewModelFactory((application as COMMApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpresaDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera os dados da Intent
        val nome = intent.getStringExtra("nome")
        val descricao = intent.getStringExtra("descricao")
        val cidade = intent.getStringExtra("cidade")
        val numAlunos = intent.getIntExtra("num_alunos", 0)
        val numVagas = intent.getIntExtra("num_vagas", 0)
        val Setor = intent.getStringExtra("setor")
        val Taxa = intent.getIntExtra("taxa",0)
        val Ano= intent.getIntExtra("ano_criacao", 0)
        val Duracao = intent.getStringExtra("duracao_estagio")


        // Configura os TextViews com os dados
        binding.empresaNome.text = nome
        binding.empresaDescricao.text = descricao
        binding.empresaCidade.text = "Cidade: $cidade"
        binding.empresaNumAlunos.text = "Alunos que estagiaram: $numAlunos"
        binding.empresaNumVagas.text = "Vagas disponíveis: $numVagas"
        binding.setor.text = "Setor: "+ Setor
        binding.taxaAc.text = "Taxa aceitação: $Taxa%"
        binding.ano.text = "Ano fundação: $Ano"
        binding.duracao.text = "Duração: "+ Duracao

        // Configurar botão de adicionar aos favoritos



        enableEdgeToEdge()
        adapter= COMMListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_comentarios)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }
    }

    fun verMapa(view: View) {
        val intent = Intent(applicationContext, Maps::class.java)
        intent.putExtra("nome", binding.empresaNome.text.toString())
        startActivity(intent)
    }

    fun back(view: View) {
        finish()
    }
}
