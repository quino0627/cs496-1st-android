package com.example.quino0627.gridlayoutexamepl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
//import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.GridLayout.VERTICAL
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rview = findViewById<View>(R.id.rview) as RecyclerView
        val place = intArrayOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,R.drawable.image_4,R.drawable.image_5,R.drawable.image_6,R.drawable.image_7,R.drawable.image_8,R.drawable.image_9,R.drawable.image_10,R.drawable.image_11,R.drawable.image_12,R.drawable.image_13,R.drawable.image_14,R.drawable.image_15,R.drawable.image_16,R.drawable.image_17,R.drawable.image_18,R.drawable.image_19,R.drawable.image_20)
        val name = arrayOf("jihun", "saerom", "jenny", "dongsuck", "sora", "dongwon", "eunwoo", "sinhye", "niko", "dongsuck", "ilaoi", "An", "sola", "jisoo", "yeri", "joy1", "joy2", "chick", "pizza", "jok")

        val lManager = GridLayoutManager(this,2,  VERTICAL, false)
        rview.setLayoutManager(lManager)

        rview.adapter = MyAdapter(place, name, this)
    }
}
