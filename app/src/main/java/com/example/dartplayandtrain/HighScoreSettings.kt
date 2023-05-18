package com.example.dartplayandtrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class HighScoreSettings : AppCompatActivity() {
    private var firebaseLink = ""
    private lateinit var starButton : Button
    private lateinit var pocetLegov : Spinner
    private lateinit var pocetRounds : Spinner
    private var pocetLegovSel = 0
    private var pocetRoundSel = 0
    /**
     * rotovanie sava
     * @param outState vyhchodzi stav
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("legs", pocetLegov.selectedItemPosition)
        outState.putInt("rounds",pocetRounds.selectedItemPosition)

    }
    /**
     * spravanie pri vytvoreni aplikacie
     * @param savedInstanceState ulozeny stav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.highscoresettings)

        // vytiahnutie databzy
       // val highScoreDb = intent.getSerializableExtra("highScoreDb") as HighScoreDatabaseHelper
        val mainIntent = intent
        starButton = findViewById<Button>(R.id.t121_start_button)
        pocetLegov = findViewById<Spinner>(R.id.drop_down_start_score)
        pocetRounds = findViewById<Spinner>(R.id.drop_down_icreased_by)
        firebaseLink = mainIntent.getStringExtra("database") ?: ""
        if (savedInstanceState != null) {
            pocetLegovSel = savedInstanceState.getInt("legs")
            pocetRoundSel = savedInstanceState.getInt("rounds")
            pocetLegov.setSelection(pocetLegovSel)
            pocetRounds.setSelection(pocetRoundSel)

        }
        starButton.setOnClickListener {

            val intent = Intent(this, HighScoreGame::class.java)
            val pocetLegovPremenna = pocetLegov.selectedItem.toString().toInt()
            val pocetRoundsPremenna = pocetRounds.selectedItem.toString().toInt()

            intent.putExtra("pocetLegov", pocetLegovPremenna)
            intent.putExtra("pocetRounds", pocetRoundsPremenna)
            intent.putExtra("database", firebaseLink)

           // intent.putExtra("highScoreDb", highScoreDb)
            startActivity(intent)

            // zatvorenie databázy
            //highScoreDb.close()
        }
    }
}