package com.dispensa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_layout.view.*

class DataAdapter(var list:ArrayList<Aliment>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameAliment=itemView.nomeAlimento
        var grammi=itemView.grammatura
        var cal = itemView.calorie
        var pro = itemView.proteine
        var carb = itemView.carboidrati
        var gra = itemView.grassi
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameAliment.text=list[position].nameAliment
        holder.grammi.text= list[position].quantita.toString()
        holder.cal.text= list[position].calorie.toString()
        holder.gra.text= list[position].grassi.toString()
        holder.pro.text= list[position].proteine.toString()
        holder.carb.text= list[position].carboidrati.toString()
    }
}