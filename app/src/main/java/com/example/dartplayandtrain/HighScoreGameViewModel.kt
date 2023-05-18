package com.example.dartplayandtrain

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class HighScoreGameViewModel : ViewModel() {
    private var context: Context? = null
    private var odohraychKol = 0
    private var najviacHodene = 0
    private var celkovonahadzane = 0

    private var input = ""
    private var rounds = 0
    private var legs = 0
    private var hodeneSkore = 0
    private var pocetOdohranychRounds = 0
    private var pocetOohranychLegov = 0
    private lateinit var database: DatabaseReference
    private var databaseUrl = ""

    private var zaokruhlenyPriemer = ""

    /**
     * vrati pocet odohratych kol
     * @return pocetOdohratychKol typu Int
     */
    fun getOdohranychKol(): Int {
        return pocetOdohranychRounds
    }
    /**
     * vrati skore ktore doposial hrac nahadzal
     * @return hodeneSkore typu Int
     */
    fun getSkore(): Int {
        return hodeneSkore
    }
    /**
     * vrati pocet odohratych legov
     * @return pocetOohranychLegov typu Int
     */
    fun getOdohranychLegs(): Int {
        return pocetOohranychLegov
    }
    /**
     * vrati hodnotu zobrazovanu ako zadavane skore
     * @return input typu String
     */
    fun getInput(): String {
        return input
    }
    /**
     * nastavi hodnotu pocetOdohranychRounds
     * @param pakolo typu String
     */
    fun setOdohranychKol(pakolo : String){
        pocetOdohranychRounds = pakolo.toInt()
    }
    /**
     * nastavi hodnotu hodeneSkore
     * @param pakolo typu String
     */
    fun setSkore(pakolo : String) {
        hodeneSkore = pakolo.toInt()
    }
    /**
     * nastavi hodnotu pocetOohranychLegov
     * @param pakolo typu String
     */
    fun setLegs(pakolo : String) {
        pocetOohranychLegov = pakolo.toInt()
    }
    /**
     * nastavi hodnotu input
     * @param pakolo typu String
     */
    fun setInput(pakolo : String) {
        input = pakolo
    }
    /**
     * nastavi hodnotu rounds a legs
     * @param paRounds typu Int
     * @param paLegs typu Int
     */
    fun initStart(paRounds : Int, paLegs:Int) {
        rounds = paRounds
        legs = paLegs
    }

    /** pridava jednotlive cislo od 0-9 do celkoveho vstupu
     * @param cislo typu String
     */
    fun addInput(cislo: String) {
        if (input.length < 3) {
            input += cislo
        }
    }

    /**
     * Sluzi na potvrdenie vstupu a kontrolu ci vstup nieje vyssi ako 180
     */
    fun potvrdInput() {
        if (input != "") {
            if (input.toInt() <= 180) {
                hodeneSkore += input.toInt()
                if (input.toInt() > najviacHodene) {
                    najviacHodene = input.toInt()
                }
                odohraychKol++
                celkovonahadzane += input.toInt()
                input = ""
                pocetOdohranychRounds++
                hernaLogika()
            }
        }
    }

    /**
     * NAstavuje databazu
     * @param databaseUrl typu String
     * @param context typu Context
     */
    fun initDatabase(databaseUrl: String, context: Context) {
        this.databaseUrl = databaseUrl
        this.context = context
        database = FirebaseDatabase.getInstance(databaseUrl).reference
    }

    /**
     * Vymaze input
     */
    fun vymazInput() {
        input = ""
    }

    /**
     * Herna logika kontroluje stav hry, v pripade splnenia parametreov ako pocet rounds a legs ukoncuje hru a zapisuje data do databazy
     */
    private fun hernaLogika() {
        if (pocetOdohranychRounds == rounds) {
            pocetOohranychLegov++
            pocetOdohranychRounds = 0
            hodeneSkore = 0

            if (pocetOohranychLegov == legs) {
                val priemer = celkovonahadzane.toFloat() / odohraychKol.toFloat()
                zaokruhlenyPriemer = String.format("%.2f", priemer)
                zapisDoDb(priemer, object : T50outViewModel.DataSavedCallback {
                    override fun onDataSaved() {
                        val alertDialogBuilder = AlertDialog.Builder(context)
                        val sprava = "Koniec\nMax skore: ${najviacHodene.toString()}\nPriemer: $zaokruhlenyPriemer"
                        alertDialogBuilder.setMessage(sprava)
                        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                            // Ukončiť aktivitu
                            (context as Activity).finish()
                        }
                        alertDialogBuilder.create().show()
                    }
                })
            }
        }
    }

    /**
     * zapisuje do firebase databazy
     * @param priemer typu Float
     * @param callback
     */
    private fun zapisDoDb(priemer: Float, callback: T50outViewModel.DataSavedCallback) {
        val identifikatorDb = "/top/denny/priemer/highScore" // Upravte identifikátor databázy podľa vašich potrieb
        database.child(identifikatorDb).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(Int::class.java)
                if (value == null || value < priemer) {
                    database.child(identifikatorDb).setValue(priemer)
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