package com.example.trab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
import com.example.trab.entities.Comentarios
import com.example.trab.viewModel.COMMViewModel
import com.example.trab.viewModel.COMMViewModelFactory

class EmpresaDetailsActivity : AppCompatActivity() {
    private lateinit var editWordView: EditText
    private lateinit var binding: ActivityEmpresaDetailsBinding
    private lateinit var adapter: COMMListAdapter

    // Inicializa o SharedPreferences para favoritos
    private val sharedPreferences by lazy {
        getSharedPreferences("favoritos", Context.MODE_PRIVATE)
    }

    private val COMMViewModel: COMMViewModel by viewModels {
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

        // Configura o campo de comentário
        /*editWordView = findViewById(R.id.input_comentario)

        // Configura o botão de envio de comentário
        val button = findViewById<Button>(R.id.btn_enviar_comentario)
        button.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)) {
                Toast.makeText(this, "Comentário não pode estar vazio", Toast.LENGTH_SHORT).show()
            } else {
                val comentario = Comentarios(null, editWordView.text.toString(), empresaName)
                COMMViewModel.insert(comentario)
                Toast.makeText(this, "Comentário Guardado", Toast.LENGTH_SHORT).show()
                editWordView.setText("") // Limpa o campo de texto após o envio
            }
        }*/

        // Configuração do botão de favoritos
        val favButton = findViewById<ImageButton>(R.id.icon_favoritar)
        updateFavoriteIcon(favButton, empresaName ?: "")

        favButton.setOnClickListener {
            toggleFavorite(favButton, empresaName ?: "")
        }
    }


    // Função para alternar o estado de favorito
    private fun toggleFavorite(button: ImageButton, empresaName: String) {
        val isFavorited = sharedPreferences.getBoolean(empresaName, false)
        if (isFavorited) {
            // Remove dos favoritos
            sharedPreferences.edit().remove(empresaName).apply()
            Toast.makeText(this, "$empresaName removida dos favoritos", Toast.LENGTH_SHORT).show()
        } else {
            // Adiciona aos favoritos
            sharedPreferences.edit().putBoolean(empresaName, true).apply()
            Toast.makeText(this, "$empresaName adicionada aos favoritos", Toast.LENGTH_SHORT).show()
        }
        updateFavoriteIcon(button, empresaName)
    }

    // Atualiza o ícone do botão de favorito
    private fun updateFavoriteIcon(button: ImageButton, empresaName: String) {
        val isFavorited = sharedPreferences.getBoolean(empresaName, false)
        if (isFavorited) {
            button.setImageResource(R.drawable.img_4) // Ícone para favorito ativo
        } else {
            button.setImageResource(R.drawable.img_3) // Ícone padrão
        }
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
