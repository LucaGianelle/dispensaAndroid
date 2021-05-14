package com.dispensa.RealtimeDatabase

import android.graphics.drawable.RippleDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispensa.ExampleItem
import com.dispensa.R
import kotlinx.android.synthetic.main.activity_one.*
import kotlinx.android.synthetic.main.example_item.*
import kotlin.random.Random

class ActivityOne : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)

        val newItem = ExampleItem(
            R.drawable.ic_android_black_24dp,
            "New item at position $index",
            "Line 2"
        )

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList [position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android_black_24dp
                1 -> R.drawable.ic_security
                else -> R.drawable.ic_account
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }

}