package com.example.dartplayandtrain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class T121settings : AppCompatActivity() {
    private var firebaseLink = ""
    private lateinit var startButton: Button
    private lateinit var startSpinner : Spinner
    private lateinit var increasedBySpinner : Spinner
    private lateinit var dartsToCloseSpinner : Spinner
    private lateinit var legsSpinner: Spinner
    /**
     * volá pri vytvorení aktivity
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t121settings)
        val mainIntent = intent

        startButton = findViewById<Button>(R.id.t121_start_button)
        startSpinner = findViewById<Spinner>(R.id.drop_down_start_score)
        increasedBySpinner = findViewById<Spinner>(R.id.drop_down_icreased_by)
        dartsToCloseSpinner = findViewById<Spinner>(R.id.drop_down_darts_close)
        legsSpinner = findViewById<Spinner>(R.id.drop_down_legs)

        firebaseLink = mainIntent.getStringExtra("database") ?: ""
        startButton.setOnClickListener {
            val intent = Intent(this, t121game::class.java)
            val startScore = startSpinner.selectedItem.toString().toInt()
            val increase = increasedBySpinner.selectedItem.toString().toInt()
            val dartToClose = dartsToCloseSpinner.selectedItem.toString().toInt()
            val legs = legsSpinner.selectedItem.toString().toInt()


            intent.putExtra("database", firebaseLink)
            intent.putExtra("startScore", startScore)
            intent.putExtra("increasedBy", increase)
            intent.putExtra("dartToClose", dartToClose)
            intent.putExtra("legs",legs)

            startActivity(intent)
        }
    }
}