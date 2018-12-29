package com.example.quino0627.tabbarsample

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_contact.*
import com.example.quino0627.tabbarsample.MainActivity.Companion.contactsList

class ContactsActivity : android.support.v4.app.Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        Log.d("hi! this is ContactsActivity", (contacts_recycler_view == null).toString())
        val rootView = inflater!!.inflate(R.layout.fragment_contact, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view) as RecyclerView
        val adapter = ContactsAdapter(contactsList!!)

        val formanage = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        return rootView

    }


}