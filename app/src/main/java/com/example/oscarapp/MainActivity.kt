package com.example.oscarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Corrigido a sintaxe aqui

        BackendServer.startServer() //Inicia o servidor
        // Redirecionar para a LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finaliza a MainActivity para que ela não permaneça na pilha de navegação
    }
}

