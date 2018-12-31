package com.example.quino0627.tabbarsample

import android.graphics.BitmapFactory
import android.graphics.Picture
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_screen_image.*
import java.io.BufferedInputStream

class FullScreenImageActivity_forMyVersion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val Picture = intent.getStringExtra("image")
        Log.d("THIS IS INTENTED IMAGE", Picture)
        val picture_uri = Uri.parse(Picture)
        //val imageView = findViewById<ImageView>(R.id.fullScreenImageView)

        val imageView = fullScreenImageView
        imageView.setImageURI(picture_uri)
        //imageView.setImageResource(imgResId)

    }

}

