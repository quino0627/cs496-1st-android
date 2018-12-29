package com.example.quino0627.tabbarsample

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_contact.*
import java.io.BufferedInputStream

class MainActivity : AppCompatActivity() {

    companion object {
        var contactsList: ArrayList<Contact>? = null
    } //used as companion object

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    val REQUEST_CONTACT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "주소록 추가하기 기능 implement", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        /*contacts*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.READ_CONTACTS), REQUEST_CONTACT)
        }else{
            setContacts()
        }

    }


    override fun onStart() {
        super.onStart()
        contactsList = setContacts()
    }

    override fun onResume() {
        super.onResume()
        contactsList = setContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position){
                0 -> {
                    return ContactsActivity()
                }
                1 -> {
                    return GalleryActivity()
                }
                2 -> {
                    return ThirdActivity()
                }
                else -> return null
            }

        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0 -> return "Contact"
                1 -> return "Gallery"
                2 -> return "SOMETHING"
            }
            return null
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CONTACT) setContacts()

    }

    fun setContacts():ArrayList<Contact> {
        val contactsList: ArrayList<Contact> = ArrayList()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        val default_photo = BitmapFactory.decodeResource(this@MainActivity.getApplicationContext().getResources(), R.drawable.profile_pic)
        var contactImage: Bitmap
        Log.d("cursor", cursor.toString())
        if(cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id)
                val photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(this@MainActivity.getContentResolver(), my_contact_Uri)
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
}
