package com.example.quino0627.tabbarsample

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_contact.*
import com.example.quino0627.tabbarsample.MainActivity.Companion.contactsList

class ContactsActivity : Fragment (){

    companion object {
        fun newInstance(): ContactsActivity {
            val fragment = ContactsActivity()
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        Log.d("hi! this is ContactsActivity", (contacts_recycler_view == null).toString())
        val rootView = inflater!!.inflate(R.layout.fragment_contact, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view) as RecyclerView
        val adapter = ContactsAdapter(contactsList!!)

        val formanage = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        //contacts_recycler_view?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
//        return inflater.inflate(R.layout.fragment_contact, container, false)
        return rootView

    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if (requestCode == REQUEST_CONTACT)setContacts()
//    }
//
//    fun setContacts() {
//
//        val contactsList: ArrayList<Contact> = ArrayList()
//        val cursor = getContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
//        while (cursor.moveToNext()) {
//            contactsList.add(
//                Contact(
//                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
//                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                )
//            )
//        }
//        cursor.close()
//        val adapter = ContactsAdapter(contactsList)
//        contacts_recycler_view.layoutManager = LinearLayoutManager(MainActivity(),LinearLayout.VERTICAL, false)
//        contacts_recycler_view.adapter = adapter
//    }

}