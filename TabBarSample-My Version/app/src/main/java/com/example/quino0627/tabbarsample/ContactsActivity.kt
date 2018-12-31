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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.example.quino0627.tabbarsample.R.id.contacts_recycler_view
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_contact.view.*
import org.jetbrains.anko.support.v4.startActivity
import java.io.BufferedInputStream

class ContactsActivity : Fragment(), ContactsAdapter.OnItemSelectedListener {

    companion object {
        var contactList1: ArrayList<Contact> = ArrayList()
        var sub_contactList :ArrayList<Contact> = ArrayList()
        var adapter : ContactsAdapter = ContactsAdapter(sub_contactList)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Check2", "Enter Contact Activity")
        val rootView = inflater!!.inflate(R.layout.fragment_contact, container, false)
        val fab = rootView.findViewById(R.id.fab) as FloatingActionButton
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view) as RecyclerView
        contactList1= setContacts()
        sub_contactList.addAll(contactList1)


        val adapter = ContactsAdapter(sub_contactList)
        adapter.setClickListener(this)
        fab.setOnClickListener {
            var intent = Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI)
            Log.d("CheckIntent", intent.toString())
            startActivity(intent)
        }
        val formanage = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)

        val editSearch = rootView.findViewById<EditText>(R.id.editSearch)
        editSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                val text : String = editSearch.text.toString()
                search(text)
                Log.d("CheckChange", sub_contactList.toString())
                recyclerView.adapter = adapter
            }
        })
        return rootView
    }

    fun search(charText : String) : ContactsAdapter {
        sub_contactList.clear()
        if (charText.length == 0) {
            sub_contactList.addAll(contactList1)
        }else{
            for(i in contactList1.indices) {
                if (contactList1[i].name.contains(charText)){
                    sub_contactList.add(contactList1[i])
                }
            }
        }
        adapter.notifyDataSetChanged()
        return adapter

    }


    override fun onResume() {
        contactList1 = setContacts()
        Log.d("Resume contactsList", contactList1.toString())
        val adapter = ContactsAdapter(contactList1)
        Log.d("Resume adapter", adapter.toString())
        adapter.setClickListener(this)

        val recyclerView = contacts_recycler_view
        val formanage = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = formanage
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        super.onResume()
    }

    fun setContacts():ArrayList<Contact> {
        val contactsList: ArrayList<Contact> = ArrayList()
        val cursor = activity!!.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY )
        val default_photo = BitmapFactory.decodeResource(activity!!.getApplicationContext().getResources(), R.drawable.profile_pic)
        var contactImage: Bitmap
        Log.d("cursor", cursor.toString())
        if(cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id)
                val photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(activity!!.getContentResolver(), my_contact_Uri)
                if (photo_stream != null) {
                    val buf = BufferedInputStream(photo_stream)
                    val btmp = BitmapFactory.decodeStream(buf)
                    contactImage = btmp
                }
                else {
                    contactImage = default_photo
                }
                contactsList.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                        contactImage
                    )
                )
            }
        }else{
            Log.d("No Contacts Avaliable", "asdf")
        }
        cursor.close()
        Log.d("contactsList", contactsList.toString())
        val adapter = ContactsAdapter(contactsList)
        Log.d("isAdapterExist?",adapter.toString())
        Log.d("isRecyclerViewNull", ((contacts_recycler_view == null).toString()))
        return contactsList
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