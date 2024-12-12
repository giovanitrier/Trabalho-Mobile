package com.example.oscarapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oscarapp.R
import com.example.oscarapp.WelcomeActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("OscarAppPrefs", MODE_PRIVATE)

        // Garantir que os usuários estão cadastrados
        inicializarUsuarios()

        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val login = findViewById<EditText>(R.id.etLogin).text.toString()
            val senha = findViewById<EditText>(R.id.etSenha).text.toString()

            if (autenticar(login, senha)) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inicializarUsuarios() {
        val usersJson = sharedPreferences.getString("user_logins", null)
        if (usersJson == null) {
            // Criar 5 usuários pré-cadastrados
            val usuarios = listOf(
                mapOf("login" to "admin", "senha" to "1234"),
                mapOf("login" to "user1", "senha" to "senha1"),
                mapOf("login" to "user2", "senha" to "senha2"),
                mapOf("login" to "user3", "senha" to "senha3"),
                mapOf("login" to "user4", "senha" to "senha4")
            )
            val gson = Gson()
            sharedPreferences.edit().putString("user_logins", gson.toJson(usuarios)).apply()
        }
    }

    private fun autenticar(login: String, senha: String): Boolean {
        val usersJson = sharedPreferences.getString("user_logins", "[]")
        val type = object : TypeToken<List<Map<String, String>>>() {}.type
        val users: List<Map<String, String>> = Gson().fromJson(usersJson, type)

        return users.any { it["login"] == login && it["senha"] == senha }
    }
}
