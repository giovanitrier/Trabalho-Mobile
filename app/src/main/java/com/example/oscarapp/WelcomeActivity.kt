package com.example.oscarapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val sharedPreferences = getSharedPreferences("OscarAppPrefs", MODE_PRIVATE)
        val token = (0..100).random()
        sharedPreferences.edit().putInt("user_token", token).apply()

        findViewById<TextView>(R.id.tvToken).text = "Seu Token: $token"

        findViewById<Button>(R.id.btnVotarFilme).setOnClickListener {
            startActivity(Intent(this, FilmeActivity::class.java))
        }
    }
}
