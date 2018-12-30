package com.example.quino0627.tabbarsample

import android.graphics.Picture
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val Picture = intent.getStringExtra("image").toInt()
        Log.d("THIS IS INTENTED IMAGE", Picture.toString())
        val imageView = findViewById<ImageView>(R.id.fullScreenImageView)
        val imgResId = Picture
        imageView.setImageResource(imgResId)

    }

}

