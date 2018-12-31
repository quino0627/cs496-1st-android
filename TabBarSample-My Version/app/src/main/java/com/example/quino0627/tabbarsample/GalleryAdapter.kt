package com.example.quino0627.tabbarsample

import android.content.Context
import android.media.Image
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import org.jetbrains.anko.sdk25.coroutines.onClick

class GalleryAdapter(private val uriList: ArrayList<String>, private val mContext: Context): RecyclerView.Adapter<GalleryAdapter.MyHolder>(){

    private lateinit var listener: GalleryAdapter.OnItemSelectedListener

    fun setClickListener(listener: GalleryAdapter.OnItemSelectedListener){
        this.listener = listener
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GalleryAdapter.MyHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.real_gallery_view,p0, false)
        return GalleryAdapter.MyHolder(v, mContext)
    }

    override fun getItemCount(): Int {
        //Log.d("CheckUrlLength", uriList.size.toString())
        return uriList.size
    }

    override fun onBindViewHolder(p0: GalleryAdapter.MyHolder, p1: Int) {
        p0.index(uriList[p1])

        p0.container.onClick {
            listener.onItemSelected(uriList[p1])
        }

    }

    class MyHolder(itemView : View, mContext: Context) : RecyclerView.ViewHolder(itemView){
        val mycontext = mContext
        val container = itemView.findViewById<View>(R.id.iview) as ImageView

        fun index(item : String){
            Glide.with(mycontext).load(item).into(container)
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(selectedImage : String)

    }

}