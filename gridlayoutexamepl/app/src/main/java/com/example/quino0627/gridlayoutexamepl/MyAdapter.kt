package com.example.quino0627.gridlayoutexamepl

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MyAdapter(private val place : IntArray, private val name : Array<String>, private val mContext : Context): RecyclerView.Adapter<MyAdapter.MyHolder>(){

    override fun getItemCount(): Int {
        return place.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyHolder, position: Int) {
        holder.index(place[position], name[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): MyAdapter.MyHolder {
        val v = LayoutInflater.from(p0.context).inflate(
            R.layout.main_page,p0,
            false
        )
        return MyHolder(v, mContext)
    }


    class MyHolder(itemView : View, private val mContext: Context) : RecyclerView.ViewHolder(itemView){
        private val iview : ImageView
        private val tview : TextView

        init {
            iview = itemView.findViewById<View>(R.id.iview) as ImageView
            tview = itemView.findViewById<View>(R.id.tview) as TextView

        }

        fun index(item : Int, s: String){
            Glide.with(mContext).load(item).into(iview)
            tview.text = s
        }
    }

}


