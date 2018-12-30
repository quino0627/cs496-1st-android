package com.example.quino0627.tabbarsample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


class ThirdActivity : Fragment (){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val rootView = inflater!!.inflate(R.layout.fragment_picture, container, false)
//        val recyclerView = rootView.findViewById(R.id.galleryview) as RecyclerView
        val rootView = inflater!!.inflate(R.layout.fragment_picture, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.galleryview) as RecyclerView
        val adapter = ThirdAdapter(MainActivity.photoList!!)
//        adapter.setClickListener(this)
        val formanage = GridLayoutManager(activity, 2, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        return rootView

        return inflater.inflate(R.layout.fragment_third, container, false)
    }
}