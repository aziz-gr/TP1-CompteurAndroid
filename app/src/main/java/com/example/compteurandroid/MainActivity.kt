package com.example.compteurandroid

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var compteur: Int = 0
    private lateinit var textViewCompteur: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restaurer la valeur après une rotation (0 si premier lancement)
        compteur = savedInstanceState?.getInt("compteur", 0) ?: 0

        textViewCompteur = findViewById(R.id.textViewCompteur)
        val buttonIncrementer = findViewById<Button>(R.id.buttonIncrementer)
        val buttonDecrementer = findViewById<Button>(R.id.buttonDecrementer)
        val buttonReinitialiser = findViewById<Button>(R.id.buttonReinitialiser)

        buttonIncrementer.setOnClickListener {
            compteur++
            afficher()
        }

        buttonDecrementer.setOnClickListener {
            compteur--
            afficher()
        }

        buttonReinitialiser.setOnClickListener {
            compteur = 0
            afficher()
            Toast.makeText(this, R.string.message_reinitialise, Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.buttonPlus5).setOnClickListener {
            compteur += 5
            afficher()
        }

        findViewById<Button>(R.id.buttonMoins5).setOnClickListener {
            compteur -= 5
            afficher()
        }

        afficher() // affiche la valeur de départ (ou restaurée)
    }


    private fun afficher() {
        textViewCompteur.text = compteur.toString()
        textViewCompteur.setTextColor(
            when {
                compteur > 0 -> Color.parseColor("#2E7D32") // vert
                compteur < 0 -> Color.parseColor("#C62828") // rouge
                else -> Color.BLACK
            }
        )
    }

    // Sauvegarder la valeur avant la destruction de l'activité (rotation)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("compteur", compteur)
    }
}