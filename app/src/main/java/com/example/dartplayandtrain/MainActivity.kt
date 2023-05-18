package com.example.dartplayandtrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var firebaseDatabaseLink = "https://dartplayandtraining-default-rtdb.europe-west1.firebasedatabase.app/"
    /**
     * spravanie pri vytvoreni aplikacie, inicializacia tlacidiel
     * @param savedInstanceState ulozeny stav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Databazy
        //val highScoreDb = HighScoreDatabaseHelper(this)


        setContentView(R.layout.activity_main)
        val newGameX01Button = findViewById<Button>(R.id.x01_menu_button)
        newGameX01Button.setOnClickListener {
            val intent = Intent(this, X01Settings::class.java)
            intent.putExtra("database",firebaseDatabaseLink)
            startActivity(intent)
        }
        val newT50out = findViewById<Button>(R.id.g50out_menu_buttton)
        newT50out.setOnClickListener {
            val intent = Intent(this, T50out::class.java)
            intent.putExtra("database",firebaseDatabaseLink)
            startActivity(intent)
        }

        val new121 = findViewById<Button>(R.id.g121_menu_button)
        new121.setOnClickListener {
            val intent = Intent(this, T121settings::class.java)
            intent.putExtra("database",firebaseDatabaseLink)
            startActivity(intent)
        }

        val newHighScoreSettings = findViewById<Button>(R.id.highScore_menu_button)
        newHighScoreSettings.setOnClickListener {
            val intent = Intent(this, HighScoreSettings::class.java)
            //intent.putExtra("highScoreDb", highScoreDb)
            intent.putExtra("database",firebaseDatabaseLink)
            startActivity(intent)
        }

        val showStatButton = findViewById<Button>(R.id.stat_menu_button)
        showStatButton.setOnClickListener {
            val intent = Intent(this, StatistikaActivity::class.java)
            intent.putExtra("database", firebaseDatabaseLink)
            startActivity(intent)
        }
    }
}