package com.example.dartplayandtrain

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider

class T50out : AppCompatActivity() {
    private lateinit var viewModel: T50outViewModel

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
    private lateinit var finish3yes: AppCompatImageButton
    private lateinit var finish6no: AppCompatImageButton
    private lateinit var finish6yes: AppCompatImageButton

    private lateinit var zobrazujInput: TextView
    private lateinit var ostavaHoditText: TextView
    private lateinit var zobrazRoundy: TextView

    /**
     * rotovanie save
     * @param outState vyhchodzi stav
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input", viewModel.getInput())
        outState.putInt("ostavaSkore", viewModel.getOstavaSkore())
        outState.putInt("rounds", viewModel.getPocetKol())

    }
    /**
     * spravanie pri vytvoreni aplikacie
     * @param savedInstanceState ulozeny stav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t50out)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(T50outViewModel::class.java)
        viewModel.initDatabase(intent.getStringExtra("database") ?: "", this)


        // Inicializácia views
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
        potvrd = findViewById(R.id.potvrd_zadane_skore_button)
        vymaz = findViewById(R.id.vymaz_zadane_skore_button)
        finish3yes = findViewById(R.id.finish_by_Nth_dart_yes)
        finish6no = findViewById(R.id.finish_by_Nth_Dart_no)
        finish6yes = findViewById(R.id.finish_by6_yes)
        zobrazujInput = findViewById(R.id.hodene_score)
        ostavaHoditText = findViewById(R.id.nahadzane_skore)
        zobrazRoundy = findViewById(R.id.t50_out_rounds_var)

        // Nastavenie listenerov na tlačidlá
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
        potvrd.setOnClickListener {
            viewModel.potvrdInput()
            updateInputuNaScreene()
        }
        vymaz.setOnClickListener {
            viewModel.vymazInput()
            updateInputuNaScreene()
        }
        finish3yes.setOnClickListener {
            viewModel.zavrel3mi()
            updateInputuNaScreene()
        }
        finish6no.setOnClickListener {
            viewModel.nezavrel6timi()
            updateInputuNaScreene()
        }
        finish6yes.setOnClickListener {
            viewModel.zavrel6mi()
            updateInputuNaScreene()
        }
        if (savedInstanceState != null) {
            val savedInput = savedInstanceState.getString("input")
            val savedOstavaSkore = savedInstanceState.getInt("ostavaSkore")
            val savedRounds = savedInstanceState.getInt("rounds")
            viewModel.setInput(savedInput.toString())
            viewModel.setOstavaSkore(savedOstavaSkore.toString())
            viewModel.setRoundy(savedRounds.toString())
        }
        updateInputuNaScreene()
    }
    /**
     * Funkcia na update textViewov na obrazovke
     */
    private fun updateInputuNaScreene() {
        zobrazujInput.text = viewModel.getInput()
        ostavaHoditText.text = viewModel.getOstavaSkore().toString()
        zobrazRoundy.text = viewModel.getPocetKol().toString()
    }
}
