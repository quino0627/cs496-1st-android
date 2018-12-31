package com.example.search_action

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editSearch = editSearch
        val listView = listView
        val list = ArrayList<String>()
        settingList()
        val arraylist = ArrayList<String>()
        arraylist.addAll(list)
        listView.adapter = adapter



    }

    private fun settingList(){
        list.add()
    }
}
