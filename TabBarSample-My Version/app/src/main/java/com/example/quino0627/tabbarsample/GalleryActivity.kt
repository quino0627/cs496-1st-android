package com.example.quino0627.tabbarsample

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.GridLayout.VERTICAL


class GalleryActivity : Fragment (), GalleryAdapter.OnItemSelectedListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d("Check2", "Enter Third Activity")

        val rootView = inflater!!.inflate(R.layout.fragment_picture, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.galleryview) as RecyclerView
        val adapter = GalleryAdapter(MainActivity.photoList!!, activity!!)

        adapter.setClickListener(this)

        val formanage = GridLayoutManager(activity, 2, VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        //return inflater.inflate(R.layout.fragment_third, container, false)
        return rootView

        //return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onItemSelected(selectedImage: String) {
        Log.d("working?", "PLZ")
        var intent = Intent(activity, FullScreenImageActivity_forMyVersion::class.java)
        intent.putExtra("image", selectedImage)
        startActivity(intent)
    }
}