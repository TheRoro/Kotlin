package com.example.agenda

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactAdapter.OnItemClickListener {

    val REQUEST_CODE = 1
    private var contacts = ArrayList<Contact>()

    private var contactAdapter = ContactAdapter(contacts, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContacts()
        initView()

    }

    private fun initView() {
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts.add(Contact("Rodrigo Ramirez", "993402864"))
        contacts.add(Contact("David Q", "993402864"))
        contacts.add(Contact("Gerardo V", "993402864"))
        contacts.add(Contact("Rodrigo Ramirez", "993402864"))
        contacts.add(Contact("David Q", "993402864"))
        contacts.add(Contact("Gerardo V", "993402864"))
        contacts.add(Contact("Rodrigo Ramirez", "993402864"))
        contacts.add(Contact("David Q", "993402864"))
        contacts.add(Contact("Gerardo V", "993402864"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, ContactActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val name = data!!.getStringExtra("keyName")
                val telephone = data.getStringExtra("keyTelephone")

                val contact = Contact(name, telephone)
                contacts.add(contact)
            }
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Contact Erased", Toast.LENGTH_SHORT).show()
        contacts.removeAt(position)
        contactAdapter.notifyDataSetChanged()
    }

}