package com.example.trab

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //FUNCAO PARA VER E DESVER A PALAVRA-PASS
        val passwordEditText = findViewById<EditText>(R.id.password)
        val showPasswordButton = findViewById<CheckBox>(R.id.check)
        passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        showPasswordButton.setOnClickListener {
            if (isPasswordVisible) {
                // Ocultar senha
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                // Mostrar senha
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT
            }
            isPasswordVisible = !isPasswordVisible
            // Move o cursor para o final do texto
            passwordEditText.setSelection(passwordEditText.text.length)
        }
        //
    }
    fun inicio(view: View) {
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        //animação transição
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


    fun Signup(view: View) {
        val intent= Intent(applicationContext, signup::class.java)
        startActivity(intent)
    }
}