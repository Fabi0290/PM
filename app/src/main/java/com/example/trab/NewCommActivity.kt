package com.example.trab

import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trab.entities.Comentarios
import com.example.trab.viewModel.COMMViewModel
import com.example.trab.viewModel.COMMViewModelFactory


class  NewCommActivity: AppCompatActivity() {
    private lateinit var editWordView: EditText

    private val COMMViewModel: COMMViewModel by viewModels {
        COMMViewModelFactory((application as COMMApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_details)
        editWordView= findViewById(R.id.input_comentario)

        val button = findViewById<Button>(R.id.btn_enviar_comentario)
        button.setOnClickListener{
            Toast.makeText(this,"Botao clicado",Toast.LENGTH_SHORT).show()
            if(TextUtils.isEmpty(editWordView.text)){
                Toast.makeText(this,"Not saved bc is empty",Toast.LENGTH_SHORT).show()

            }else{
                var Comentario = Comentarios(null, editWordView.text.toString())//,editWordView3.text.toString())
                COMMViewModel.insert(Comentario)
                finish()
            }
        }

    }

    fun back(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}