package com.example.quino0627.tabbarsample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_contact.*
import java.io.BufferedInputStream

class MainActivity : AppCompatActivity() {

    companion object {
        var photoList: ArrayList<String>? = null
    } //used as companion object

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    val MULTIPLE_PERMISSIONS = 10;
    val permissions = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val REQUEST_CONTACT = 1
    val REQUEST_READ_STORAGE = 1
    val REQUEST_CALL = 1
    val REQUEST_SMS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

<<<<<<< HEAD
//        ActivityCompat.requestPermissions(this, permissions, MULTIPLE_PERMISSIONS)
=======
>>>>>>> 8cc1a2312b464d65f0b6711471d1f155e7b7015f


        ActivityCompat.requestPermissions(this, permissions, MULTIPLE_PERMISSIONS)
    }

    override fun onStart() {
        super.onStart()
        photoList = setPhotos()
    }

    override fun onResume() {
        super.onResume()
        photoList = setPhotos()
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
        if (requestCode == REQUEST_READ_STORAGE) setPhotos()

    }

    fun setPhotos(): ArrayList<String> {
        val photoList : ArrayList<String> = ArrayList()
        Log.d("Check1", "Doing setPhotos in MainActivity")
        //val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Images.ImageColumns.DATE_TAKEN + "DESC")
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
            projection,
            null, null, null
        )// DATA를 출력
        // 모든 개체 출력

        Log.d("Cursor in setPhotos", cursor.toString())

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val dataColumnIndex = cursor.getColumnIndex(projection[0])
                val filePath = cursor.getString(dataColumnIndex)
                val imageUri = Uri.parse(filePath)
                //photoList.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
                photoList.add(imageUri.toString())

                Log.d("There is Photo in setPhotos", imageUri.toString())

                //photoList.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
            }
        }
        cursor.close()
        val adapter = ThirdAdapter(photoList, this)
        Log.d("isAdapterExist?",adapter.toString())
        return photoList
    }
}
