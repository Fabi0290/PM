package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.adapter.COMMListAdapter
import com.example.trab.databinding.ActivityEmpresaDetailsBinding
import com.example.trab.entities.Comentarios
import com.example.trab.viewModel.COMMViewModel
import com.example.trab.viewModel.COMMViewModelFactory

class EmpresaDetailsActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText
    private lateinit var binding: ActivityEmpresaDetailsBinding
    private lateinit var adapter: COMMListAdapter
    private lateinit var Media: TextView

    private val COMMViewModel: COMMViewModel by viewModels {
        COMMViewModelFactory((application as COMMApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpresaDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera os dados da Intent
        val nome = intent.getStringExtra("nome")?:""
        val descricao = intent.getStringExtra("descricao")
        val cidade = intent.getStringExtra("cidade")
        val numAlunos = intent.getIntExtra("num_alunos", 0)
        val numVagas = intent.getIntExtra("num_vagas", 0)
        val Setor = intent.getStringExtra("setor")
        val Taxa = intent.getIntExtra("taxa", 0)
        val Ano = intent.getIntExtra("ano_criacao", 0)
        val Duracao = intent.getStringExtra("duracao_estagio")
        val empresaName = nome // Armazenando o nome da empresa para uso posterior

        // Configura os TextViews com os dados
        binding.empresaNome.text = nome
        binding.empresaDescricao.text = descricao
        binding.empresaCidade.text = "Cidade: $cidade"
        binding.empresaNumAlunos.text = "Alunos que estagiaram: $numAlunos"
        binding.empresaNumVagas.text = "Vagas disponíveis: $numVagas"
        binding.setor.text = "Setor: $Setor"
        binding.taxaAc.text = "Taxa aceitação: $Taxa%"
        binding.ano.text = "Ano fundação: $Ano"
        binding.duracao.text = "Duração: $Duracao"

        // Configuração da RecyclerView
        enableEdgeToEdge()
        adapter = COMMListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_comentarios)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa os comentários associados à empresa
        if (empresaName != null) {
            COMMViewModel.getComment(empresaName).observe(this) { comentarios ->
                comentarios.let { adapter.submitList(comentarios) }
            }
        }



        // Observando os comentários no ViewModel
        COMMViewModel.getComment(empresaName).observe(this, Observer { comentarios ->
            // Verifica se 'comentarios' não é nulo e não está vazio
            if (comentarios != null && comentarios.isNotEmpty()) {
                val validComentarios = comentarios.filter { it.estrelas != null && it.estrelas?.toDouble() != null }

                if (validComentarios.isNotEmpty()) {
                    // Calcula a soma das estrelas válidas como Double
                    val totalEstrelas = validComentarios.sumOf { it.estrelas?.toDouble() ?: 0.0 }
                    val mediaRatings = totalEstrelas / validComentarios.size
                    val mediaTextView: TextView = findViewById(R.id.media)
                    mediaTextView.text = "%.2f".format(mediaRatings)
                } else {
                    val mediaTextView: TextView = findViewById(R.id.media)
                    mediaTextView.text = "N/A"
                }
            } else {
                // Caso não haja comentários
                val mediaTextView: TextView = findViewById(R.id.media)
                mediaTextView.text = "N/A"
            }
        })





    }


    // Função para abrir o mapa com o nome da empresa
    fun verMapa(view: View) {
        val intent = Intent(applicationContext, Maps::class.java)
        intent.putExtra("nome", binding.empresaNome.text.toString())
        startActivity(intent)
    }

    // Função para voltar para a tela anterior
    fun back(view: View) {
        finish()
    }

    // Função para adicionar um comentário
    fun adicionarcomm(view: View) { // Pega o nome da empresa atual
        val intent = Intent(applicationContext, Coment::class.java)
        intent.putExtra("nome", binding.empresaNome.text.toString())
        intent.putExtra("cidade", binding.empresaCidade.text.toString())
        startActivity(intent)
    }
}
