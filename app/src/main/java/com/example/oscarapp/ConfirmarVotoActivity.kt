package com.example.oscarapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmarVotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar_voto)

        val sharedPreferences = getSharedPreferences("OscarAppPrefs", MODE_PRIVATE)
        val votos = sharedPreferences.getString("user_votos", "{}")
        val tokenStored = sharedPreferences.getInt("user_token", -1)

        findViewById<TextView>(R.id.tvVotos).text = "Votos: $votos"

        findViewById<Button>(R.id.btnConfirmar).setOnClickListener {
            val tokenInput = findViewById<EditText>(R.id.etToken).text.toString().toIntOrNull()
            if (tokenInput == tokenStored) {
                Toast.makeText(this, "Votos confirmados!", Toast.LENGTH_SHORT).show()
                sharedPreferences.edit().remove("user_votos").apply()
            } else {
                Toast.makeText(this, "Token inválido!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
