package com.example.quino0627.tabbarsample

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GalleryAdapter(private val place: IntArray, private val name: Array<String>, private val mContext: Context): RecyclerView.Adapter<GalleryAdapter.MyHolder>(){

    override fun getItemCount(): Int {
        return place.size
    }

    override fun onBindViewHolder(holder: GalleryAdapter.MyHolder, position: Int) {
        holder.index(place[position], name[position])
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): GalleryAdapter.MyHolder {
        val v = LayoutInflater.from(p0.context).inflate(
            R.layout.gallery_view,p0,
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