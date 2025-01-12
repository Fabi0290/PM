package com.example.trab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trab.adapter.FavoritosAdapter

class perfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        // Configuração para bordas
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_perfil)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar favoritos do SharedPreferences
        val sharedPreferences = getSharedPreferences("favoritos", Context.MODE_PRIVATE)
        val favoritos = sharedPreferences.all.filter { it.value as Boolean }.keys.toList()

        // Configurar o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FavoritosAdapter(favoritos)
    }

    fun inicio(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        // Animação de transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
