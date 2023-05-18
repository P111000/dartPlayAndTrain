package com.example.dartplayandtrain

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider

class HighScoreGame : AppCompatActivity() {
    // viewModel
    private lateinit var viewModel: HighScoreGameViewModel
    // tlacidla
    private lateinit var tlacidlo0: Button
    private lateinit var tlacidlo1: Button
    private lateinit var tlacidlo2: Button
    private lateinit var tlacidlo3: Button
    private lateinit var tlacidlo4: Button
    private lateinit var tlacidlo5: Button
    private lateinit var tlacidlo6: Button
    private lateinit var tlacidlo7: Button
    private lateinit var tlacidlo8: Button
    private lateinit var tlacidlo9: Button
    private lateinit var potvrd: AppCompatImageButton
    private lateinit var vymaz: AppCompatImageButton
    //textviews
    private lateinit var roundsTextView: TextView
    private lateinit var legsTextView: TextView
    private lateinit var hodeneTextView: TextView
    private lateinit var nahadzaneTextView: TextView

    /**
     * rotovanie sava
     * @param outState vyhchodzi stav
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("rounds", viewModel.getOdohranychKol().toString())
        outState.putString("legs", viewModel.getOdohranychLegs().toString())
        outState.putString("input", viewModel.getInput())
        outState.putString("nahadzane", viewModel.getSkore().toString())

    }

    /**
     * spravanie pri vytvoreni aplikacie
     * @param savedInstanceState ulozeny stav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.highscoregame)
        val intent = intent
        //viewModel = ViewModelProvider(this).get(HighScoreGameViewModel::class.java)
        //viewModel.initialize(rounds, legs, databaseUrl)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HighScoreGameViewModel::class.java)
        viewModel.initDatabase(intent.getStringExtra("database") ?: "", this)
        viewModel.initStart(intent.getIntExtra("pocetRounds",10), intent.getIntExtra("pocetLegov", 7))
        tlacidlo0 = findViewById(R.id.cislo_0)
        tlacidlo1 = findViewById(R.id.cislo_1)
        tlacidlo2 = findViewById(R.id.cislo_2)
        tlacidlo3 = findViewById(R.id.cislo_3)
        tlacidlo4 = findViewById(R.id.cislo_4)
        tlacidlo5 = findViewById(R.id.cislo_5)
        tlacidlo6 = findViewById(R.id.cislo_6)
        tlacidlo7 = findViewById(R.id.cislo_7)
        tlacidlo8 = findViewById(R.id.cislo_8)
        tlacidlo9 = findViewById(R.id.cislo_9)

        tlacidlo0.setOnClickListener {
            viewModel.addInput("0")
            updateInputuNaScreene()
        }
        tlacidlo1.setOnClickListener {
            viewModel.addInput("1")
            updateInputuNaScreene()
        }
        tlacidlo2.setOnClickListener {
            viewModel.addInput("2")
            updateInputuNaScreene()
        }
        tlacidlo3.setOnClickListener {
            viewModel.addInput("3")
            updateInputuNaScreene()
        }
        tlacidlo4.setOnClickListener {
            viewModel.addInput("4")
            updateInputuNaScreene()
        }
        tlacidlo5.setOnClickListener {
            viewModel.addInput("5")
            updateInputuNaScreene()
        }
        tlacidlo6.setOnClickListener {
            viewModel.addInput("6")
            updateInputuNaScreene()
        }
        tlacidlo7.setOnClickListener {
            viewModel.addInput("7")
            updateInputuNaScreene()
        }
        tlacidlo8.setOnClickListener {
            viewModel.addInput("8")
            updateInputuNaScreene()
        }
        tlacidlo9.setOnClickListener {
            viewModel.addInput("9")
            updateInputuNaScreene()
        }
        potvrd = findViewById(R.id.potvrd_zadane_skore_button)
        vymaz = findViewById(R.id.vymaz_zadane_skore_button)

        potvrd.setOnClickListener {
            viewModel.potvrdInput()
            updateInputuNaScreene()
        }
        vymaz.setOnClickListener {
            viewModel.vymazInput()
            updateInputuNaScreene()
        }

        roundsTextView = findViewById<TextView>(R.id.rounds_zobrazovanie)
        legsTextView = findViewById<TextView>(R.id.legs_zobrazovanie)
        hodeneTextView = findViewById<TextView>(R.id.hodene_score)
        nahadzaneTextView = findViewById<TextView>(R.id.nahadzane_skore)
        // nacitanie stavu pri otoveni
        if (savedInstanceState != null) {
            val savedInput = savedInstanceState.getString("input")
            val savedOstavaSkore = savedInstanceState.getString("nahadzane")
            val savedRounds = savedInstanceState.getString("rounds")
            val savedLegs = savedInstanceState.getString("legs")

            if (savedInput != null) {
                viewModel.setInput(savedInput)

            }
            if (savedLegs != null) {
                viewModel.setLegs(savedLegs)
            }

            if (savedRounds != null) {
                viewModel.setOdohranychKol(savedRounds)
            }

            if (savedOstavaSkore != null) {
                viewModel.setSkore(savedOstavaSkore)
            }

        }
        updateInputuNaScreene()
    }

    /**
     * Funkcia na update textViewov na obrazovke
     */
    private fun updateInputuNaScreene() {
        roundsTextView.text = viewModel.getOdohranychKol().toString()
        legsTextView.text = viewModel.getOdohranychLegs().toString()
        hodeneTextView.text = viewModel.getInput()
        nahadzaneTextView.text = viewModel.getSkore().toString()
    }
}