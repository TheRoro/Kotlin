package com.example.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_contact.view.*

class ContactAdapter(
        var contacts: ArrayList<Contact>,
        val listener: OnItemClickListener): RecyclerView.Adapter<ContactAdapter.ContactPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_contact, parent, false)

        return ContactPrototype(view)
    }

    override fun onBindViewHolder(contactPrototype: ContactPrototype, position: Int) {
        contactPrototype.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tvName = itemView.tvName
        val tvPhone = itemView.tvPhone

        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvPhone.text = contact.telephone
        }
        init {
            itemView.eraseBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}