package com.dispensa.RealtimeDatabase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dispensa.ExampleItem
import com.dispensa.MainActivity
import com.dispensa.R
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(
    private val exampleList: List<ExampleItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView1.text = currentItem.text2

        if (position == 0) {
            holder.textView1.setBackgroundColor(Color.YELLOW)
        } else {

        }
    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2

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