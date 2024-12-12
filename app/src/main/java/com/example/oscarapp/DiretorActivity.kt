package com.example.oscarapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class DiretorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diretor)

        val diretores = listOf("Diretor A", "Diretor B", "Diretor C")
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupDiretores)

        diretores.forEachIndexed { index, diretor ->
            val radioButton = RadioButton(this).apply {
                text = diretor
                id = View.generateViewId()  // Gerar um ID único para cada RadioButton
            }
            radioGroup.addView(radioButton)
        }

        findViewById<Button>(R.id.btnConfirmarVotoDiretor).setOnClickListener {
            val selecionado = radioGroup.checkedRadioButtonId
            if (selecionado != -1) {
                votarDiretor(selecionado)
            } else {
                Toast.makeText(this, "Selecione um diretor!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun votarDiretor(diretorId: Int) {
        val sharedPreferences = getSharedPreferences("OscarAppPrefs", MODE_PRIVATE)
        val votos = sharedPreferences.getString("user_votos", "{}")
        val votosMap = Gson().fromJson(votos, MutableMap::class.java) as MutableMap<String, Int>
        votosMap["diretor"] = diretorId
        sharedPreferences.edit().putString("user_votos", Gson().toJson(votosMap)).apply()

        Toast.makeText(this, "Você votou no diretor!", Toast.LENGTH_SHORT).show()
    }
}
