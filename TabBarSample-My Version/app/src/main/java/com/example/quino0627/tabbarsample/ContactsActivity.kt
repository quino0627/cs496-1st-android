package com.example.quino0627.tabbarsample

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
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
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.startActivity
import java.io.BufferedInputStream

class ContactsActivity : Fragment(), ContactsAdapter.OnItemSelectedListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Check2", "Enter Contact Activity")
        val rootView = inflater!!.inflate(R.layout.fragment_contact, container, false)
        val fab = rootView.findViewById(R.id.fab) as FloatingActionButton
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view) as RecyclerView
        val adapter = ContactsAdapter(contactsList!!)
        adapter.setClickListener(this)

        fab.setOnClickListener {
            var intent = Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI)
            Log.d("CheckIntent", intent.toString())
            //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            activity?.finish()
//            var intent1 = Intent(activity!!, MainActivity::class.java)
//            startActivity(intent1)


//            intent = Intent(activity!!, MainActivity::class.java)
//            startActivity(intent)
            //view -> Snackbar.make(view, "주소록 추가하기 기능 implement", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }




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