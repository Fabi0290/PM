package com.example.trab

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trab.entities.Comentarios
import com.example.trab.viewModel.COMMViewModel
import com.example.trab.viewModel.COMMViewModelFactory

class Coment : AppCompatActivity() {
    private lateinit var editWordView: EditText
    private lateinit var Nome: TextView
    private lateinit var ratingBar: RatingBar


    // Inicialize a variável empresaName apenas após pegar o valor de "nome" da Intent
    private lateinit var empresaName: String


    private val COMMViewModel: COMMViewModel by viewModels {
        COMMViewModelFactory((application as COMMApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coment)

        // Recupera o nome da Intent
        empresaName = intent.getStringExtra("nome") ?: ""

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Nome = findViewById(R.id.nomeEmpresa)
        Nome.setText(empresaName)
        // Comments
        editWordView = findViewById(R.id.input_comentario)
        ratingBar = findViewById(R.id.rating_bar)

        // Btn comments
        val button = findViewById<Button>(R.id.btn_enviar_comentario)
        button.setOnClickListener {
            // Ve se o campo comentário esta vazio
            if (TextUtils.isEmpty(editWordView.text)) {
                Toast.makeText(this, "Comentário não pode estar vazio", Toast.LENGTH_SHORT).show()
            } else {
                val rating = ratingBar.rating
                // Cria um novo comentário e insere no ViewModel
                val comentario = Comentarios(null, editWordView.text.toString(), empresaName,estrelas = rating)
                COMMViewModel.insert(comentario)
                Toast.makeText(this, "Comentário Guardado", Toast.LENGTH_SHORT).show()

                // Limpa
                editWordView.setText("")
                finish()
            }
        }
    }

    // Função para voltar
    fun back(view: View) {
        finish()
    }
}
