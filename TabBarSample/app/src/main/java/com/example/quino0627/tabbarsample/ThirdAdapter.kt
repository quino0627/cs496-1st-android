package com.example.quino0627.tabbarsample

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.quino0627.tabbarsample.R.id.iview
import com.example.quino0627.tabbarsample.R.id.tview

class ThirdAdapter(private val uriList: ArrayList<String>): RecyclerView.Adapter<ThirdAdapter.MyHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ThirdAdapter.MyHolder {
        val v = LayoutInflater.from(p0.context).inflate(
            R.layout.activity_full_screen_image,p0,
            false
        )
        return ThirdAdapter.MyHolder(v)
    }

    override fun getItemCount(): Int {
        return uriList.size
    }

    override fun onBindViewHolder(p0: ThirdAdapter.MyHolder, p1: Int) {

    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val container = itemView.findViewById<ImageView>(R.id.fullScreenImageView)
    }
}