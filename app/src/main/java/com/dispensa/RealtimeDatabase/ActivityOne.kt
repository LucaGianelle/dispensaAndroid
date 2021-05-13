package com.dispensa.RealtimeDatabase

import android.graphics.drawable.RippleDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dispensa.ExampleItem
import com.dispensa.R

class ActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
    }

    private fun generateDummyList(size: Int): List<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android_black_24dp
                1 -> RippleDrawable.
                else -> R.drawable.ic_sun
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
        }

    }
}