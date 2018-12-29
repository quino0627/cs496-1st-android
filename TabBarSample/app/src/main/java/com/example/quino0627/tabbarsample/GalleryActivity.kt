package com.example.quino0627.tabbarsample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout


class GalleryActivity : Fragment (){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_picture, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.galleryview) as RecyclerView

        val place = intArrayOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,R.drawable.image_4,R.drawable.image_5,R.drawable.image_6,R.drawable.image_7,R.drawable.image_8,R.drawable.image_9,R.drawable.image_10,R.drawable.image_11,R.drawable.image_12,R.drawable.image_13,R.drawable.image_14, R.drawable.image_15, R.drawable.image_16,R.drawable.image_17,R.drawable.image_18,R.drawable.image_19,R.drawable.image_20)
        val name = arrayOf("jihun", "saerom", "jenny", "dongsuck", "sora", "dongwon", "eunwoo", "sinhye", "niko", "dongsuck", "ilaoi", "An", "sola", "jisoo", "yeri", "joy1", "joy2", "chick", "pizza", "jok")
        val asdf = activity!!
        val adapter = GalleryAdapter(
            place,
            name,
            asdf
        )

        val lManager = GridLayoutManager(activity, 2, GridLayout.VERTICAL, false)

        recyclerView.layoutManager = lManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)

        return rootView
    }
}