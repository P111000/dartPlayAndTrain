package com.example.dartplayandtrain


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider


class X01hra : AppCompatActivity() {
    private lateinit var viewModel: X01hraViewModel

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
    private lateinit var menoHraca1TextView : TextView
    private lateinit var menoHraca2TextView : TextView
    private lateinit var skoreHrac1TextView : TextView
    private lateinit var skoreHrac2TextView : TextView
    private lateinit var zobrazujInput : TextView

    //SETY A LEGY v XML
    private lateinit var pocetVyhranychSetovHrac1TextView: TextView
    private lateinit var pocetVyhranychSetovHrac2TextView: TextView
    private lateinit var pocetVyhranychLegovHrac1TextView: TextView
    private lateinit var pocetVyhranychLegovHrac2TextView: TextView

    /**
     * rotovanie save
     * @param outState vyhchodzi stav
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input", viewModel.getInput())

        outState.putInt("pocetVyhratychSetovHrac1", viewModel.getPocetVyhratychSetovHrac1())
        outState.putInt("pocetVyhratychSetovHrac2", viewModel.getPocetVyhratychSetovHrac2())

        outState.putInt("skoreHrac1", viewModel.getSkoreHrac1())
        outState.putInt("skoreHrac2", viewModel.getSkoreHrac2())

        outState.putInt("pocetVyhratychLegovHrac1", viewModel.getPocetVyhratychLegovHrac1())
        outState.putInt("pocetVyhratychLegovHrac2", viewModel.getPocetVyhratychLegovHrac2())

        outState.putInt("hracNaRade", viewModel.getHracNaRade())

        outState.putInt("pocetOdohranychLegov", viewModel.getPocetOdohranychLegov())
        outState.putInt("pocetOdohranychSetov", viewModel.getPocetOdohranychSetov())

        outState.putInt("pociatocneSkoreHrac1", viewModel.getPociatocneSkoreHrac1())
        outState.putInt("pociatocneSkoreHrac2", viewModel.getPociatocneSkoreHrac2())

        outState.putInt("hra", viewModel.getHra())

        outState.putInt("pocetSetov", viewModel.getPocetSetov())
        outState.putInt("pocetLegov", viewModel.getPocetLegov())
    }

    /**
     * volá pri vytvorení aktivity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.x01game)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(X01hraViewModel::class.java)

        viewModel.initDatabase(intent.getStringExtra("database") ?: "", this)
        zobrazujInput = findViewById(R.id.hodene_score)
        pocetVyhranychSetovHrac1TextView = findViewById<TextView>(R.id.vyhraneSetyHrac1)
        pocetVyhranychSetovHrac2TextView = findViewById<TextView>(R.id.vyhraneSetyHrac2)
        pocetVyhranychLegovHrac1TextView = findViewById<TextView>(R.id.vyhraneLegyHrac1)
        pocetVyhranychLegovHrac2TextView = findViewById<TextView>(R.id.vyhraneLegyHrac2)


        menoHraca1TextView = findViewById<TextView>(R.id.meno_hrac1)
        menoHraca2TextView = findViewById<TextView>(R.id.meno_hrac2)

        skoreHrac1TextView = findViewById<TextView>(R.id.zobrazuj_skore_hraca1)
        skoreHrac2TextView = findViewById<TextView>(R.id.zobrazuj_skore_hraca2)
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

        viewModel.initialize(intent.getStringExtra("hrac1_meno") ?: "Player 1",intent.getStringExtra("hrac2_meno") ?: "Player 2",intent.getIntExtra("pocetSetov", 1),intent.getIntExtra("pocetLegov",3),intent.getIntExtra("typHry", 501),intent.getIntExtra("handicapHodnotaHrac1", 0),intent.getIntExtra("handicapHodnotaHrac2", 0), intent.getBooleanExtra("hrac1HandicapBool", false), intent.getBooleanExtra("hrac2HandicapBool", false))
        updateInputuNaScreene()
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

        if (savedInstanceState != null) {
            val savedInput = savedInstanceState.getString("input")

            val savedPocetVyhratychSetov1 = savedInstanceState.getInt("pocetVyhratychSetovHrac1")
            val savedPocetVyhratychSetov2 = savedInstanceState.getInt("pocetVyhratychSetovHrac2")

            val savedSkoreHrac1 = savedInstanceState.getInt("skoreHrac1")
            val savedSkoreHrac2 = savedInstanceState.getInt("skoreHrac2")

            val savedPocetVyhratychLeegocHrac1 = savedInstanceState.getInt("pocetVyhratychLegovHrac1")
            val savedPocetVyhratychLeegocHrac2 = savedInstanceState.getInt("pocetVyhratychLegovHrac2")

            val hracNaRade = savedInstanceState.getInt("hracNaRade")

            val pocetOdohranychLegov = savedInstanceState.getInt("pocetOdohranychLegov")
            val pocetOdohranychSetov = savedInstanceState.getInt("pocetOdohranychSetov")

            val pociatocneSkoreHrac1 = savedInstanceState.getInt("pociatocneSkoreHrac1")
            val pociatocneSkoreHrac2 = savedInstanceState.getInt("pociatocneSkoreHrac2")

            val hra = savedInstanceState.getInt("hra")

            val pocetSetov = savedInstanceState.getInt("pocetSetov")
            val pocetLegov = savedInstanceState.getInt("pocetLegov")
            if (savedInput != null) {
                viewModel.setInput(savedInput)

                viewModel.setPocetVyhratychSetovHrac1(savedPocetVyhratychSetov1)
                viewModel.setPocetVyhratychSetovHrac2(savedPocetVyhratychSetov2)

                viewModel.setSkoreHrac1(savedSkoreHrac1)
                viewModel.setSkoreHrac2(savedSkoreHrac2)

                viewModel.setPocetVyhratychLegovHrac1(savedPocetVyhratychLeegocHrac1)
                viewModel.setPocetVyhratychLegovHrac2(savedPocetVyhratychLeegocHrac2)

                viewModel.setHracNaRade(hracNaRade)

                viewModel.setPocetOdohranychLegov(pocetOdohranychLegov)
                viewModel.setPocetOdohranychSetov(pocetOdohranychSetov)

                viewModel.setPociatocneSkoreHrac1(pociatocneSkoreHrac1)
                viewModel.setPociatocneSkoreHrac2(pociatocneSkoreHrac2)
                viewModel.setHra(hra)
                viewModel.setPocetLegov(pocetLegov)
                viewModel.setPocetSetov(pocetSetov)
            }

        }
        updateInputuNaScreene()
    }

    /**
     * Funkcia na update textViewov na obrazovke
     */
    private fun updateInputuNaScreene() {
        zobrazujInput.text = viewModel.getInput()
        menoHraca1TextView.text = viewModel.getMenoHrac1()
        menoHraca2TextView.text = viewModel.getMenoHrac2()
        skoreHrac1TextView.text = viewModel.getSkoreHrac1().toString()
        skoreHrac2TextView.text = viewModel.getSkoreHrac2().toString()

        pocetVyhranychLegovHrac1TextView.text = viewModel.getPocetVyhratychLegovHrac1().toString()
        pocetVyhranychLegovHrac2TextView.text = viewModel.getPocetVyhratychLegovHrac2().toString()

        pocetVyhranychSetovHrac1TextView.text = viewModel.getPocetVyhratychSetovHrac1().toString()
        pocetVyhranychSetovHrac2TextView.text = viewModel.getPocetVyhratychSetovHrac2().toString()
    }
}

