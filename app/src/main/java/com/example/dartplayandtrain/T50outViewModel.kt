package com.example.dartplayandtrain

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

/**
 * spracovanie logiky hry T50out
 */
class T50outViewModel : ViewModel() {
    private var context: Context? = null
    private var ostavaSkore = 0
    private var pociatocneSkore = 50
    private var pocetKol = 0
    private var input = ""
    private lateinit var database: DatabaseReference
    private var databaseUrl = ""

    init {
        ostavaSkore = pociatocneSkore
    }
    interface DataSavedCallback {
        fun onDataSaved()
    }

    /**
     *s lúži na pridanie čísla do vstupu
     */
    fun addInput(cislo: String) {
        if (input.length < 3) {
            input += cislo
        }
    }

    /**
     * overuje a spracováva vstup
     */
    fun potvrdInput() {
        if (input != "") {
            if (input.toInt() <= 180) {
                if (ostavaSkore >= input.toInt()) {
                    ostavaSkore -= input.toInt()
                    input = ""
                    pocetKol++
                }
            }
        }
    }

    /**
     * slúži na nastavenie vstupu
     * @param ulozeny
     */
    fun setInput(ulozeny :String) {
        input = ulozeny
    }

    /**
     * slúži na nastavenie zostávajúceho skóre
     * @param ulozeneSkore
     */
    fun setOstavaSkore(ulozeneSkore :String) {
        ostavaSkore = ulozeneSkore.toInt()
    }

    /**
     * nastavenie počtu kôl
     * @param ulozeneRounds
     */
    fun setRoundy(ulozeneRounds : String) {
        pocetKol = ulozeneRounds.toInt()
    }

    /**
     * slúži na vymazanie vstupu
     */
    fun vymazInput() {
        input = ""
    }

    /**
     *  Metóda sa vykonáva, ak je zostávajúce skóre  nulové. V tomto prípade sa k pôvodnému skóre  pridá 2 a vykoná sa herná logika
     */
    fun zavrel3mi() {
        if (ostavaSkore == 0) {
            pociatocneSkore += 2
            hernaLogika()
        }
    }
    /**
     *  Metóda sa vykonáva, ak je zostávajúce skóre  nulové. V tomto prípade sa k pôvodnému skóre  pridá 1 a vykoná sa herná logika
     */
    fun zavrel6mi() {
        if (ostavaSkore == 0) {
            pociatocneSkore += 1
            hernaLogika()
        }
    }

    /**
     *  Metóda sa vykonáva, ak je zostávajúce skóre  nulové. V tomto prípade sa od pôvodného skóre odčíta 1 a vykoná sa herná logika
     */
    fun nezavrel6timi() {
        if (ostavaSkore == 0) {
            pociatocneSkore -= 1
            hernaLogika()
        }
    }

    /**
     * vráti zostávajúce skóre
     */
    fun getOstavaSkore(): Int {
        return ostavaSkore
    }

    /**
     * vráti počet kôl
     */
    fun getPocetKol(): Int {
        return pocetKol
    }

    /**
     *  vráti vstup
     */
    fun getInput(): String {
        return input
    }

    /**
     * inicializuje pripojenie k databáze
     * @param databaseUrl
     * @param context
     */
    fun initDatabase(databaseUrl: String, context: Context) {
        this.databaseUrl = databaseUrl
        this.context = context
        database = FirebaseDatabase.getInstance(databaseUrl).reference
    }

    /**
     * vykonáva hernú logiku
     */
    private fun hernaLogika() {
        if (pociatocneSkore > 60) {
            vyhravas()
        } else if (pociatocneSkore < 40) {
            prehravas()
        }
        noveKolo()
    }

    /**
     * nastavuje nové kolo
     */
    private fun noveKolo() {
        ostavaSkore = pociatocneSkore
    }

    /**
     *  volá sa v prípade výhry
     */
    private fun vyhravas() {
        zapisDoDb(pocetKol, object : DataSavedCallback {
            override fun onDataSaved() {
                val alertDialogBuilder = AlertDialog.Builder(context)
                val sprava = "Vyhravas \nPocet kol: $pocetKol"
                alertDialogBuilder.setMessage(sprava)
                alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                    // Ukončiť aktivitu
                    (context as Activity).finish()
                }
                alertDialogBuilder.create().show()
            }
        })
    }

    /**
     *  volá sa v prípade prehry
     */
    private fun prehravas() {
        zapisDoDb(pocetKol, object : DataSavedCallback {
            override fun onDataSaved() {
                val alertDialogBuilder = AlertDialog.Builder(context)
                val sprava = "Prehravas \nPocet kol: $pocetKol"
                alertDialogBuilder.setMessage(sprava)
                alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
                    // Ukončiť aktivitu
                    (context as Activity).finish()
                }
                alertDialogBuilder.create().show()
            }
        })
    }

    /**
     * zapisuje počet kôl do databázy
     * @param pocetKol
     * @param callback
     */
    private fun zapisDoDb(pocetKol: Int, callback: DataSavedCallback) {

        val identifikatorDb = "/top/denny/priemer/t50out" // Upravte identifikátor databázy podľa vašich potrieb

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
