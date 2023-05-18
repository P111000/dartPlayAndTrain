package com.example.dartplayandtrain


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference

class X01hraViewModel : ViewModel() {

    private var context: Context? = null
    private var menoHrac1: String = ""
    private var menoHrac2: String = ""
    private var pocetSetov: Int = 0
    private var pocetLegov: Int = 0
    private var hra: Int = 0
    private var pociatocneSkoreHrac1: Int = 0
    private var pociatocneSkoreHrac2: Int = 0
    private var skoreHrac1: Int = 0
    private var skoreHrac2: Int = 0
    private var pocetOdohranychSetov: Int = 0
    private var pocetOdohranychLegov: Int = 0
    private var pocetVyhratychSetovHrac1: Int = 0
    private var pocetVyhratychSetovHrac2: Int = 0
    private var pocetVyhratychLegovHrac1: Int = 0
    private var pocetVyhratychLegovHrac2: Int = 0
    private var input = ""
    private lateinit var database: DatabaseReference
    private var databaseUrl = ""

    private var naRadeJeHrac = 1
    private var zacaitokHry = 1

    /**
     * inicializáciu premenných a nastavenie parametrov hry
     * @param menoH1
     * @param menoH2
     * @param pocLegov
     * @param pocSetov
     * @param pociatSkoreH1
     * @param pociatSkoreH2
     * @param hendicapHRac1
     * @param hendicapHRac2
     * @param hraType
     */
    fun initialize(
        menoH1: String,
        menoH2: String,
        pocSetov: Int,
        pocLegov: Int,
        hraType: Int,
        pociatSkoreH1: Int,
        pociatSkoreH2: Int,
        hendicapHRac1: Boolean,
        hendicapHRac2: Boolean

    ) {
        menoHrac1 = menoH1
        menoHrac2 = menoH2
        pocetSetov = pocSetov
        pocetLegov = pocLegov
        hra = hraType

        pociatocneSkoreHrac1 = hra
        pociatocneSkoreHrac2 = hra
        if (hendicapHRac1) {
            pociatocneSkoreHrac1 = pociatSkoreH1
        }
        if (hendicapHRac2) {
            pociatocneSkoreHrac2 = pociatSkoreH2
        }
        skoreHrac1 = pociatocneSkoreHrac1
        skoreHrac2 = pociatocneSkoreHrac2
        naRadeJeHrac = 1
        input = ""
    }

    /**
     * getter pre menoHrac1
     * @return menoHrac1
     */
    fun getMenoHrac1(): String {
        return menoHrac1
    }

    /**
     * getter pre menoHrac2
     * @return menoHrac2
     */
    fun getMenoHrac2(): String {
        return menoHrac2
    }

    /**
     * getter pre pocetSetov
     * @return pocetSetov
     */
    fun getPocetSetov(): Int {
        return pocetSetov
    }
    /**
     * getter pre pocetLegov
     * @return pocetLegov
     */
    fun getPocetLegov(): Int {
        return pocetLegov
    }
    /**
     * getter pre hra
     * @return hra
     */

    fun getHra(): Int {
        return hra
    }
    /**
     * getter pre pociatocneSkoreHrac1
     * @return pociatocneSkoreHrac1
     */
    fun getPociatocneSkoreHrac1(): Int {
        return pociatocneSkoreHrac1
    }
    /**
     * getter pre pociatocneSkoreHrac2
     * @return pociatocneSkoreHrac2
     */
    fun getPociatocneSkoreHrac2(): Int {
        return pociatocneSkoreHrac2
    }
    /**
     * getter pre skoreHrac1
     * @return skoreHrac1
     */
    fun getSkoreHrac1(): Int {
        return skoreHrac1
    }
    /**
     * getter pre skoreHrac2
     * @return skoreHrac2
     */
    fun getSkoreHrac2(): Int {
        return skoreHrac2
    }
    /**
     * getter pre pocetOdohranychSetov
     * @return pocetOdohranychSetov
     */
    fun getPocetOdohranychSetov(): Int {
        return pocetOdohranychSetov
    }
    /**
     * getter pre pocetOdohranychLegov
     * @return pocetOdohranychLegov
     */
    fun getPocetOdohranychLegov(): Int {
        return pocetOdohranychLegov
    }
    /**
     * getter pre pocetVyhratychSetovHrac1
     * @return pocetVyhratychSetovHrac1
     */
    fun getPocetVyhratychSetovHrac1(): Int {
        return pocetVyhratychSetovHrac1
    }
    /**
     * getter pre pocetVyhratychSetovHrac2
     * @return pocetVyhratychSetovHrac2
     */
    fun getPocetVyhratychSetovHrac2(): Int {
        return pocetVyhratychSetovHrac2
    }
    /**
     * getter pre pocetVyhratychLegovHrac1
     * @return pocetVyhratychLegovHrac1
     */
    fun getPocetVyhratychLegovHrac1(): Int {
        return pocetVyhratychLegovHrac1
    }
    /**
     * getter pre pocetVyhratychLegovHrac2
     * @return pocetVyhratychLegovHrac2
     */
    fun getPocetVyhratychLegovHrac2(): Int {
        return pocetVyhratychLegovHrac2
    }
    /**
     * getter pre input
     * @return input
     */
    fun getInput(): String {
        return input
    }

    /**
     * seter pre MenoHraca1
     * @param meno
     */
    fun setMenoHrac1(meno: String) {
        menoHrac1 = meno
    }
    /**
     * seter pre MenoHraca2
     * @param meno
     */
    fun setMenoHrac2(meno: String) {
        menoHrac2 = meno
    }
    /**
     * seter pre pocetSetov
     * @param pocet
     */
    fun setPocetSetov(pocet: Int) {
        pocetSetov = pocet
    }

    /**
     * seter pre pocetLegov
     * @param pocet
     */
    fun setPocetLegov(pocet: Int) {
        pocetLegov = pocet
    }
    /**
     * seter pre hra
     * @param hra
     */
    fun setHra(hra: Int) {
        this.hra = hra
    }
    /**
     * seter pre pociatocneSkoreHrac1
     * @param skore
     */
    fun setPociatocneSkoreHrac1(skore: Int) {
        pociatocneSkoreHrac1 = skore
    }
    /**
     * seter pre pociatocneSkoreHrac2
     * @param skore
     */
    fun setPociatocneSkoreHrac2(skore: Int) {
        pociatocneSkoreHrac2 = skore
    }
    /**
     * seter pre skoreHrac1
     * @param skore
     */
    fun setSkoreHrac1(skore: Int) {
        skoreHrac1 = skore
    }
    /**
     * seter pre skoreHrac2
     * @param skore
     */
    fun setSkoreHrac2(skore: Int) {
        skoreHrac2 = skore
    }
    /**
     * seter pre pocetOdohranychSetov
     * @param pocet
     */
    fun setPocetOdohranychSetov(pocet: Int) {
        pocetOdohranychSetov = pocet
    }
    /**
     * seter pre pocetOdohranychLegov
     * @param pocet
     */
    fun setPocetOdohranychLegov(pocet: Int) {
        pocetOdohranychLegov = pocet
    }
    /**
     * seter pre pocetVyhratychSetovHrac1
     * @param pocet
     */
    fun setPocetVyhratychSetovHrac1(pocet: Int) {
        pocetVyhratychSetovHrac1 = pocet
    }
    /**
     * seter pre pocetVyhratychSetovHrac2
     * @param pocet
     */
    fun setPocetVyhratychSetovHrac2(pocet: Int) {
        pocetVyhratychSetovHrac2 = pocet
    }
    /**
     * seter pre pocetVyhratychLegovHrac1
     * @param pocet
     */
    fun setPocetVyhratychLegovHrac1(pocet: Int) {
        pocetVyhratychLegovHrac1 = pocet
    }
    /**
     * seter pre pocetVyhratychLegovHrac2
     * @param pocet
     */
    fun setPocetVyhratychLegovHrac2(pocet: Int) {
        pocetVyhratychLegovHrac2 = pocet
    }
    /**
     * seter pre input
     * @param parameter
     */
    fun setInput(parameter: String) {
        input = parameter
    }

    /**
     * vracia naRadeJeHrac
     * @return naRadeJeHrac
     */
    fun getHracNaRade() : Int {
        return naRadeJeHrac
    }
    /**
     * seter pre naRadeJeHrac
     * @param parameter
     */
    fun setHracNaRade(parameter: Int) {
        naRadeJeHrac = parameter
    }



    private fun hernaLogika() {
        if (skoreHrac1 == 0) {
            pocetVyhratychLegovHrac1++
            if (pocetVyhratychLegovHrac1 == pocetLegov) {
                pocetVyhratychLegovHrac1 = 0
                pocetVyhratychSetovHrac1++
                if (pocetVyhratychSetovHrac1 == pocetSetov) {
                    vypisVytaza(menoHrac1)
                }
            }
            novaHra()
        }
        else if (skoreHrac2 == 0) {
            pocetVyhratychLegovHrac2++
            if (pocetVyhratychLegovHrac2 == pocetLegov) {
                pocetVyhratychLegovHrac2 = 0
                pocetVyhratychSetovHrac2++
                if (pocetVyhratychSetovHrac2 == pocetSetov) {
                    vypisVytaza(menoHrac2)
                }
            }
            novaHra()
        }
    }
    private fun novaHra() {
        // prehodenie startu
        if (zacaitokHry == 1) {
            zacaitokHry = 2
        } else if (zacaitokHry == 2) {
            zacaitokHry = 1
        }
        if (zacaitokHry == 1) {
            naRadeJeHrac = 1
        } else if (zacaitokHry == 2) {
            naRadeJeHrac = 2
        }
        skoreHrac1 = pociatocneSkoreHrac1
        skoreHrac2 = pociatocneSkoreHrac2

    }

    /**
     *  pridáva vstupné číslo do reťazca input
     *  @param cislo
     */
    fun addInput(cislo: String) {
        if (input.length < 3) {
            input += cislo
        }
    }

    /**
     * inicializuje databázu
     * @param databaseUrl
     * @param context
     */
    fun initDatabase(databaseUrl: String, context: Context) {
        this.databaseUrl = databaseUrl
        this.context = context
    }

    /**
     * aktualizuje skoreHrac1
     * @param odpocitajVstup
     */
    private fun updateSkoreHrac1(odpocitajVstup: String) {
        skoreHrac1 = skoreHrac1 - odpocitajVstup.toInt()
    }
    /**
     * aktualizuje skoreHrac2
     * @param odpocitajVstup
     */
    private fun updateSkoreHrac2(odpocitajVstup: String) {
        skoreHrac2 = skoreHrac2 - odpocitajVstup.toInt()
    }
    /**
     * potvrdzuje vstup
     */
    fun potvrdInput() {
        if (input != "") {
            if (input.toInt() <= 180) {
                if (naRadeJeHrac == 1) {
                    if (skoreHrac1 >= input.toInt()) {
                        updateSkoreHrac1(input)
                        input = ""
                        naRadeJeHrac = 2
                    }
                } else {
                    if (skoreHrac2 >= input.toInt()) {
                        updateSkoreHrac2(input)
                        input = ""
                        naRadeJeHrac = 1
                    }
                }
            }
            hernaLogika()
        }
    }
    /**
     *  vymaže vstupný reťazec
     */
    fun vymazInput() {
        input = ""
    }

    /**
     *  zobrazuje dialógové okno s výsledkom hry, kde sa zobrazuje meno víťaza
     *  @param vyherca
     */
    private fun vypisVytaza(vyherca: String) {

        val alertDialogBuilder = AlertDialog.Builder(context)
        val sprava = "Vyhrava $vyherca"
        alertDialogBuilder.setMessage(sprava)
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            // Ukončiť aktivitu
        (context as Activity).finish()
                }
        alertDialogBuilder.create().show()


    }

}