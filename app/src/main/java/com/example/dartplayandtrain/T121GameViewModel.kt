package com.example.dartplayandtrain

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class T121GameViewModel : ViewModel() {
    private var context: Context? = null
    private var startScore = 0
    private var increasedBy = 0
    private var dartsToClose = 0
    private var upraveneSkore = 0
    private var ostavaSkore = 0
    private var input = ""
    private var legs = 0
    private var odohratychLegov = 0
    private var najvacsieZatvorenie = 0

    private lateinit var database: DatabaseReference
    private var databaseUrl = ""

    interface DataSavedCallback {
        fun onDataSaved()
    }

    /**
     * inicializuje hru s parametrami
     * @param startScore
     * @param increasedBy
     * @param dartsToClose
     * @param legs
     */
    fun initialize(startScore: Int, increasedBy: Int, dartsToClose: Int, legs: Int) {
        this.startScore = startScore
        this.increasedBy = increasedBy
        this.dartsToClose = dartsToClose
        this.legs = legs

        upraveneSkore = startScore
        ostavaSkore = upraveneSkore
    }

    /**
     * pridáva vstup
     * @param cislo
     */
    fun addInput(cislo: String) {
        if (input.length < 3) {
            input += cislo
        }
    }

    /**
     * potvrdzuje vstup
     */
    fun potvrdInput() {
        if (input.isNotEmpty()) {
            val inputValue = input.toIntOrNull()
            inputValue?.let {
                if (inputValue <= 180 && ostavaSkore >= inputValue) {
                    ostavaSkore -= inputValue
                    input = ""
                }
            }
        }
    }

    /**
     *  vymaže vstupný reťazec
     */
    fun vymazInput() {
        input = ""
    }

    /**
     * vracia input
     * @return input
     */
    fun getInput() : String {
        return input
    }

    /**
     * vracia ostavaSkore
     * @return ostavaSkore
     */
    fun getOstavaHodit() : String {
        return ostavaSkore.toString()
    }

    /**
     * vracia legs
     * @return legs
     */
    fun getLegs() : String {
        return legs.toString()
    }

    /**
     * vracia pocet sipok na zatvorenie
     * @return dartsToClose
     */
    fun getNthDartClose() : String {
        return dartsToClose.toString()
    }

    /**
     * Metóda sa volá, keď je uzavretá N-tá sipka
     */

    fun zavrelNthSipku() {
        if (upraveneSkore > najvacsieZatvorenie) {
            najvacsieZatvorenie = upraveneSkore
        }
        upraveneSkore += increasedBy
        hernaLogika()
    }

    /**
     * Metóda sa volá, keď N-tá sipka nie je uzavretá.
     */
    fun nezavrelNthuSipku() {
        upraveneSkore = startScore
        hernaLogika()
    }

    /**
     * inicializuje databázu
     * @param databaseUrl
     * @param context
     */
    fun initDatabase(databaseUrl: String, context: Context) {
        this.databaseUrl = databaseUrl
        this.context = context
        database = FirebaseDatabase.getInstance(databaseUrl).reference
    }

    /**
     * reprezentuje hernú logiku pre hru T121
     *
     */
    private fun hernaLogika() {
        if (odohratychLegov == legs - 1) {
            // End the game
            zapisDoDb(najvacsieZatvorenie, object : T50outViewModel.DataSavedCallback {
                override fun onDataSaved() {
                    val alertDialogBuilder = AlertDialog.Builder(context)
                    val sprava = "Koniec \nNajvacsie zatvorenie: $najvacsieZatvorenie"
                    alertDialogBuilder.setMessage(sprava)
                    alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                        // Ukončiť aktivitu
                        (context as Activity).finish()
                    }
                    alertDialogBuilder.create().show()
                }
            })

        } else if (ostavaSkore == 0) {
            // Start a new leg
            odohratychLegov++
            ostavaSkore = upraveneSkore
        }

    }

    /**
     * zapisuje záznam o najväčšom zatvorení do databázy
     * @param pocetKol
     * @param callback
     */
    private fun zapisDoDb(pocetKol: Int, callback: T50outViewModel.DataSavedCallback) {

        val identifikatorDb = "/top/denny/priemer/t121/"

        database.child(identifikatorDb).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(Int::class.java)
                if (value == null || value > pocetKol) {
                    database.child(identifikatorDb).setValue(pocetKol)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                println("Dáta boli úspešne zaznamenané do databázy.")
                            } else {
                                println("Chyba pri zápise do databázy: ${task.exception?.message}")
                            }
                        }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println("Chyba pri čítaní z databázy: ${error.message}")
            }
        })
        callback.onDataSaved()
    }

}
