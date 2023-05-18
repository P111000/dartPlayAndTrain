package com.example.dartplayandtrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class X01Settings : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var menoHraca1Input :TextInputEditText
    private lateinit var menoHraca2Input :TextInputEditText
    private lateinit var handicapCheckBoxHrac1 : CheckBox
    private lateinit var handicapCheckBoxHrac2 : CheckBox
    private lateinit var hraSpinner : Spinner
    private lateinit var hraHandicapHrac1 : Spinner
    private lateinit var hraHandicapHrac2 : Spinner
    private lateinit var legSpinner : Spinner
    private lateinit var setSpinner : Spinner
    /**
     * volá pri vytvorení aktivity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.x01settings)
        startButton = findViewById(R.id.startButtonX01)
        menoHraca1Input = findViewById<TextInputEditText>(R.id.input_meno_hraca1)
        menoHraca2Input = findViewById<TextInputEditText>(R.id.input_meno_hraca2)
        handicapCheckBoxHrac1 = findViewById<CheckBox>(R.id.checkbox_handicapHrac1)
        handicapCheckBoxHrac2 = findViewById<CheckBox>(R.id.checkbox_handicapHrac2)
        hraSpinner = findViewById<Spinner>(R.id.dropMenuPreVyberHier)
        hraHandicapHrac1 = findViewById<Spinner>(R.id.handicapHrac1Drop)
        hraHandicapHrac2 = findViewById<Spinner>(R.id.handicapHrac2Drop)
        legSpinner = findViewById<Spinner>(R.id.dropMenuPrePocetLegov)
        setSpinner = findViewById<Spinner>(R.id.dropMenuPrePocetSetov)

        startButton.setOnClickListener{
            val intent = Intent(this, X01hra::class.java)
            // mena hracov
            val menoHraca1 = menoHraca1Input.text.toString()
            val menoHraca2 =  menoHraca2Input.text.toString()
            // handicap
            val handicapHrac1 = handicapCheckBoxHrac1.isChecked
            val handicapHrac2 = handicapCheckBoxHrac2.isChecked
            // zistenie hry
            val akaHra = hraSpinner.selectedItem.toString().toInt()
            // zistenieHandicapHry
            val handicapDruhHryHrac1 = hraHandicapHrac1.selectedItem.toString().toInt()
            val handicapDruhHryHrac2 = hraHandicapHrac2.selectedItem.toString().toInt()
            // nastavenie hry
            val pocetLegov = legSpinner.selectedItem.toString().toInt()
            val pocetSetov = setSpinner.selectedItem.toString().toInt()

            // Inity

            intent.putExtra("hrac1_meno", menoHraca1)
            intent.putExtra("hrac2_meno", menoHraca2)

            intent.putExtra("hrac1HandicapBool", handicapHrac1)
            intent.putExtra("hrac2HandicapBool", handicapHrac2)

            intent.putExtra("typHry", akaHra)

            intent.putExtra("handicapHodnotaHrac1", handicapDruhHryHrac1)
            intent.putExtra("handicapHodnotaHrac2", handicapDruhHryHrac2)

            intent.putExtra("pocetLegov", pocetLegov)
            intent.putExtra("pocetSetov", pocetSetov)

            startActivity(intent)
        }
    }
}