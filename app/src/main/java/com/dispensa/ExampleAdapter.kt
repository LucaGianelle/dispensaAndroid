package com.dispensa

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*
import kotlinx.android.synthetic.main.row.view.*

class ExampleAdapter(
    private val exampleList: List<Aliment>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.nomeCibo.text = currentItem.nameAliment
        holder.calorieCibo.text = currentItem.calorie.toString()
        holder.quantitaCibo.text = currentItem.quantita.toString()
        holder.proteineCibo.text = currentItem.proteine.toString()
        holder.grassiCibo.text = currentItem.grassi.toString()
        holder.carboidratiCibo.text = currentItem.carboidrati.toString()

    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val nomeCibo: TextView = itemView.textViewNomeCibo
        val calorieCibo: TextView = itemView.textViewCalorie
        val proteineCibo: TextView = itemView.grammiProteine
        val grassiCibo: TextView = itemView.grammiGrassi
        val carboidratiCibo: TextView = itemView.grammiCarboidrati
        val quantitaCibo: TextView = itemView.textViewQuantita


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