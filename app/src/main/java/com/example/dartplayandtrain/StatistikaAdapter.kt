package com.example.dartplayandtrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatistikaAdapter : RecyclerView.Adapter<StatistikaAdapter.StatistikaViewHolder>() {
    private val data: MutableList<Statistika> = ArrayList()

    /**
     * Sluzi na pridelenie dat
     * @param statistikaList
     */
    fun setData(statistikaList: List<Statistika>) {
        data.clear()
        data.addAll(statistikaList)
        notifyDataSetChanged()
    }

    /**
     * áto funkcia vytvára nový StatistikaViewHolder, ktorý slúži na zobrazenie jednej položky v zozname štatistík.
     * @param parent
     * @param viewType
     * @return viewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatistikaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statistika, parent, false)
        return StatistikaViewHolder(view)
    }

    /**
     * táto funkcia zabezpečuje, že dáta pre každú položku v zozname sú správne zobrazované a aktualizované.
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: StatistikaViewHolder, position: Int) {
        val statistika = data[position]
        holder.bind(statistika)
    }

    /**
     * vracia velkost zoznamu
     * @return velkost zoznamu
     */
    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * parametricky konstruktor triedy StatistikaViewHolder
     * @param itemView
     */
    class StatistikaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val highScoreTextView: TextView = itemView.findViewById(R.id.highScoreTextView)
        private val t121TextView: TextView = itemView.findViewById(R.id.t121TextView)
        private val t50outTextView: TextView = itemView.findViewById(R.id.t50outTextView)

        /**
         * slúži na naplnenie zobrazenia (view) dátami z objektu statistika.
         * @param statistika
         */
        fun bind(statistika: Statistika) {
            highScoreTextView.text = "Dnesne maximalne hodene skore v High Score: ${statistika.highScore}"
            t121TextView.text = "Dnesne maximalne zavretie v T121: ${statistika.t121}"
            t50outTextView.text = "Dnesny minimalny pocet kol v T50 Out: ${statistika.t50out}"
        }
    }
}
