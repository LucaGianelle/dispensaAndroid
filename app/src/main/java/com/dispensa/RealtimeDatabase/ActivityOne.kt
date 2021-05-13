package com.dispensa.RealtimeDatabase

import android.graphics.drawable.RippleDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dispensa.ExampleItem
import com.dispensa.R
import kotlinx.android.synthetic.main.activity_one.*

class ActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val exampleList = generateDummyList(500)

        recycler_view.adapter = ExampleAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<ExampleItem> {

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