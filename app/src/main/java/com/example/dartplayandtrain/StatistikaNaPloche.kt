package com.example.dartplayandtrain

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Implementation of App Widget functionality.
 */
class StatistikaNaPloche : AppWidgetProvider() {
    /**
     * metóda je volaná pri aktualizácii widgetu
     * @param context
     * @param appWidgetIds
     * @param appWidgetManager
     */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            loadStatisticsFromDatabase(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     *  Táto metóda je volaná pri povolení widgetu
     *  @param context
     */
    override fun onEnabled(context: Context) {
    }

    /**
     * Táto metóda je volaná pri zakázaní widgetu
     * @param context
     */
    override fun onDisabled(context: Context) {
    }

    /**
     * metóda slúži na načítanie štatistík z databázy a aktualizáciu widgetu s novými hodnotami
     * @param context
     * @param appWidgetId
     * @param appWidgetManager
     */
    private fun loadStatisticsFromDatabase(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://dartplayandtraining-default-rtdb.europe-west1.firebasedatabase.app/top/denny/priemer")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var highScore = "0"
                var t121 = "0"
                var t50out = "0"
                for (dataSnapshot in snapshot.children) {
                    val key = dataSnapshot.key
                    val value = dataSnapshot.getValue(Long::class.java).toString()

                    if (key == "highScore") {
                        highScore = value
                    } else if (key == "t121") {
                        t121 = value
                    } else if (key == "t50out") {
                        t50out = value
                    }
                }

                val statistics = "Denná štatistika: High Score - $highScore, T121 - $t121, T50out - $t50out"

                // Konštrukcia RemoteViews objektu
                val views = RemoteViews(context.packageName, R.layout.statistika_na_ploche)
                views.setTextViewText(R.id.appwidget_text, statistics)

                // Aktualizácia widgetu
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            override fun onCancelled(error: DatabaseError) {
                // Spracovanie chyby pri načítaní dát z databázy
            }
        })
    }
}
