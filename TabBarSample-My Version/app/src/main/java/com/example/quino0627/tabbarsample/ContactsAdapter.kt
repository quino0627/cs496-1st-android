package com.example.quino0627.tabbarsample

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener
import android.widget.AdapterView
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk25.coroutines.onClick

class ContactsAdapter( val contactsList: ArrayList<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){

    private lateinit var listener: OnItemSelectedListener

    fun setClickListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ContactsAdapter.ViewHolder, position: Int) {
        holder.bountItem(contactsList[position])
        Glide.with(holder.container).load(contactsList[position].image).into(holder.profilePic)
        holder.container.onClick {
            listener.onItemSelected(contactsList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val profilePic = itemView.findViewById<ImageView>(R.id.iv_profile)
        var textName = itemView.findViewById<AppCompatTextView>(R.id.contact_name)
        var textPhoneNumber = itemView.findViewById<AppCompatTextView>(R.id.contact_phone_number)
        val container = itemView.findViewById<CardView>(R.id.container)
        fun bountItem(contact: Contact) {
            val textName = itemView.findViewById<AppCompatTextView>(R.id.contact_name) as AppCompatTextView
            val textPhoneNumber = itemView.findViewById<AppCompatTextView>(R.id.contact_phone_number) as AppCompatTextView

            val imageView = itemView.findViewById(R.id.iv_profile) as ImageView

            Log.d("qqqqq",contact.image.toString())
            textName.text = contact.name
            textPhoneNumber.text = contact.phoneNumber
            imageView.setImageBitmap(contact.image)

        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(selectedContact: Contact)
    }
}