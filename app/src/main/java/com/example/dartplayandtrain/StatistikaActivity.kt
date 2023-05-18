package com.example.dartplayandtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StatistikaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var statistikaAdapter: StatistikaAdapter
    private lateinit var dbRef: DatabaseReference
    /**
     * spravanie pri vytvoreni aplikacie
     * @param savedInstanceState ulozeny stav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistika)

        recyclerView = findViewById(R.id.statistikaRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        statistikaAdapter = StatistikaAdapter()
        recyclerView.adapter = statistikaAdapter

        dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dartplayandtraining-default-rtdb.europe-west1.firebasedatabase.app/top/denny/priemer")
        fetchDataFromFirebase()
    }

    /**
     * funkcia sluziaca na vytahovanie dat z firebase databazy
     */
    private fun fetchDataFromFirebase() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val statistikaList = mutableListOf<Statistika>()
                var highScore = "0"
                var t121 = "0"
                var t50out = "0"
                for (dataSnapshot in snapshot.children) {
                    val key = dataSnapshot.key
                    val value = dataSnapshot.getValue(Long::class.java).toString()

                    if (key == "highScore") {
                        highScore = value.toString()
                        // Provádějte další operace s hodnotou highScore
                    } else if (key == "t121") {
                        t121 = value.toString()
                        // Provádějte další operace s hodnotou t121
                    } else if (key == "t50out") {
                        t50out = value.toString()
                        // Provádějte další
                    }
                }
                val statistika = Statistika(highScore, t121, t50out)
                statistikaList.add(statistika)

                statistikaAdapter.setData(statistikaList)


            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
