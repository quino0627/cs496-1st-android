package com.example.quino0627.tabbarsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class ForTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("CheckStart", "EnterTest")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_third)
    }
}