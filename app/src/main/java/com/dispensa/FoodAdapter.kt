package com.dispensa


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class FoodAdapter(private val exampleList: List<Aliment>, private val listener: OnItemClickListener) :
        RecyclerView.Adapter<FoodAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.nomeCibo.text = currentItem.nameAliment
        holder.calorieCibo.text = currentItem.calorie
        holder.quantitaCibo.text = currentItem.quantita
        holder.proteineCibo.text = currentItem.proteine
        holder.grassiCibo.text = currentItem.grassi
        holder.carboidratiCibo.text = currentItem.carboidrati

    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val nomeCibo: TextView = itemView.textViewNomeCibo
        val calorieCibo: TextView = itemView.textViewCalorie
        val quantitaCibo: TextView = itemView.textViewQuantita
        val proteineCibo: TextView = itemView.grammiProteine
        val grassiCibo: TextView = itemView.grammiGrassi
        val carboidratiCibo: TextView = itemView.grammiCarboidrati

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface  OnItemClickListener {
        fun onItemClick(position: Int)
    }
}