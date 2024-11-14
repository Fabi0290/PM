package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun login(view: View) {
        val intent= Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }

    fun termos(view: View) {
        val intent= Intent(applicationContext, policies::class.java)
        startActivity(intent)
    }

    fun mapa(view: View) {
        val intent= Intent(applicationContext, mapa::class.java)
        startActivity(intent)
    }

    fun empresas(view: View) {
        val intent= Intent(applicationContext, empresas::class.java)
        startActivity(intent)
    }
}