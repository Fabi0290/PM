package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    //ARRAY LISTA EMPRESAS


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
        val intent= Intent(applicationContext, Maps::class.java)
        startActivity(intent)
    }

    fun empresas(view: View) {
        val intent= Intent(applicationContext, empresas::class.java)
        startActivity(intent)
    }


}