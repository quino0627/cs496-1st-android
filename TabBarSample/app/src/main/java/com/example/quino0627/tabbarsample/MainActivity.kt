package com.example.quino0627.tabbarsample

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_contact.*

class MainActivity : AppCompatActivity() {

    companion object {
        var contactsList: ArrayList<Contact>? = null
    } //used as companion object

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    val REQUEST_CONTACT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        Log.d("MAO", "\n\nMainActivity Oncreate\n\n")


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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0 -> return "HOME"
                1 -> return "ABOUT US"
                2 -> return "CONTACT US"
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
        Log.d("cursor", cursor.toString())
        if(cursor.count > 0) {
            while (cursor.moveToNext()) {
                contactsList.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
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
        //var mRV = findViewById(R.id.contacts_recycler_view) as RecyclerView
        //contacts_recycler_view?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL, false)
        //contacts_recycler_view?.adapter = adapter
        return contactsList
    }
}
