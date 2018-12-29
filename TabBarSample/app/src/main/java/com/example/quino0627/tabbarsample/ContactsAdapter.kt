package com.example.quino0627.tabbarsample

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

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


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textName = itemView.findViewById<AppCompatTextView>(R.id.contact_name)
        var textPhoneNumber = itemView.findViewById<AppCompatTextView>(R.id.contact_phone_number)

        fun bountItem(contact: Contact) {
            val textName = itemView.findViewById<AppCompatTextView>(R.id.contact_name) as AppCompatTextView
            val textPhoneNumber = itemView.findViewById<AppCompatTextView>(R.id.contact_phone_number) as AppCompatTextView


            textName.text = contact.name
            textPhoneNumber.text = contact.phoneNumber

        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(selectedContact: Contact)
    }
}