package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class policies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_policies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_policies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}