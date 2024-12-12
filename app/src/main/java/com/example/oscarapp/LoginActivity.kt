package com.example.oscarapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val login = findViewById<EditText>(R.id.etLogin).text.toString()
            val senha = findViewById<EditText>(R.id.etSenha).text.toString()

            if (login.isNotEmpty() && senha.isNotEmpty()) {
                autenticarUsuario(login, senha)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun autenticarUsuario(login: String, senha: String) {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val request = LoginRequest(login, senha)

        apiService.autenticar(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.success) {
                        Toast.makeText(this@LoginActivity, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login ou senha inválidos", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Erro no servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
