package com.example.quino0627.tabbarsample

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_contact.*
import com.example.quino0627.tabbarsample.MainActivity.Companion.contactsList

class ContactsActivity : Fragment(), ContactsAdapter.OnItemSelectedListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val rootView = inflater!!.inflate(R.layout.fragment_contact, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view) as RecyclerView
        val adapter = ContactsAdapter(contactsList!!)
        adapter.setClickListener(this)
        val formanage = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        return rootView

    }

    override fun onItemSelected(selectedContact: Contact) {
        Log.d("WTF", "WHY NOT WORKING?!?")
        var intent = Intent(activity, ContactDetailsActivity::class.java)
        intent.putExtra("name", selectedContact.name)
        intent.putExtra("phone", selectedContact.phoneNumber)
        intent.putExtra("imageUrl", selectedContact.image.toString())
        startActivity(intent)


    }

}