package com.example.dartplayandtrain

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider


class t121game : AppCompatActivity() {
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
    private lateinit var finishNthYes: AppCompatImageButton
    private lateinit var finishNthNo: AppCompatImageButton
    private lateinit var zobrazujInput: TextView
    private lateinit var OstavaHoditText: TextView
    private lateinit var legsTextView: TextView
    private lateinit var finishedBynThDartsTextView: TextView

    private lateinit var viewModel: T121GameViewModel

    /**
     * volá pri vytvorení aktivity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t121game)

        viewModel = ViewModelProvider(this).get(T121GameViewModel::class.java)

        val startScore = intent.getIntExtra("startScore", 121)
        val increasedBy = intent.getIntExtra("increasedBy", 10)
        val dartsToClose = intent.getIntExtra("dartToClose", 9)
        val legs = intent.getIntExtra("legs", 9)

        viewModel.initialize(startScore, increasedBy, dartsToClose, legs)
        viewModel.initDatabase(intent.getStringExtra("database") ?: "", this)

        initializeViews()
        setupClickListeners()
        updateInputuNaScreene()
    }

    /**
     * Inicializuje zobrazené prvky na obrazovke
     */
    private fun initializeViews() {
        zobrazujInput = findViewById(R.id.hodene_score)
        OstavaHoditText = findViewById(R.id.nahadzane_skore)
        legsTextView = findViewById(R.id.t121_legs_var)
        finishedBynThDartsTextView = findViewById(R.id.t121_finished_by_9_text_var)

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
        finishNthYes = findViewById(R.id.finish_by_Nth_dart_yes)
        finishNthNo = findViewById(R.id.finish_by_Nth_Dart_no)
    }

    /**
     *  Nastavuje poslucháče kliknutia
     */
    private fun setupClickListeners() {
        tlacidlo0.setOnClickListener {
            viewModel.addInput("0")
            updateInputuNaScreene()
        }
        tlacidlo1.setOnClickListener {
            viewModel.addInput("1")
            updateInputuNaScreene() }
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
        finishNthYes.setOnClickListener {
            viewModel.zavrelNthSipku()
            updateInputuNaScreene()
        }
        finishNthNo.setOnClickListener {
            viewModel.nezavrelNthuSipku()
            updateInputuNaScreene()
        }
    }

    /**
     * Aktualizuje zobrazený text v TextView
     */
    private fun updateInputuNaScreene() {
        zobrazujInput.text = viewModel.getInput()
        OstavaHoditText.text = viewModel.getOstavaHodit()
        legsTextView.text = viewModel.getLegs()
        updateFinishedBynThDartsText(viewModel.getNthDartClose().toInt())
    }

    /**
     * Aktualizuje text v TextView pre zobrazenie, či bolo dosiahnuté ukončenie hry v konkrétnom počte hodov
     * @param value
     */
    private fun updateFinishedBynThDartsText(value: Int) {
        finishedBynThDartsTextView.text = "Finished by $value darts?"
    }
}