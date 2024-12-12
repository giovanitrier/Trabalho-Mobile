package com.example.oscarapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class FilmeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme)

        val filmes = listOf(
            Filme(1, "Filme A", "Ação", "https://link/para/posterA.jpg"),
            Filme(2, "Filme B", "Drama", "https://link/para/posterB.jpg")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFilmes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilmeAdapter(filmes) { filme ->
            votarFilme(filme.id)
        }
    }

    private fun votarFilme(filmeId: Int) {
        val sharedPreferences = getSharedPreferences("OscarAppPrefs", MODE_PRIVATE)
        val votos = sharedPreferences.getString("user_votos", "{}")
        val votosMap = Gson().fromJson(votos, MutableMap::class.java) as MutableMap<String, Int>

        // Usar a chave dinâmica para cada filme
        votosMap["filme_$filmeId"] = filmeId
        sharedPreferences.edit().putString("user_votos", Gson().toJson(votosMap)).apply()

        Toast.makeText(this, "Você votou no filme!", Toast.LENGTH_SHORT).show()
    }
}
