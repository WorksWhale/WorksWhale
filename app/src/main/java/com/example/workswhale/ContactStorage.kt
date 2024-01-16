package com.example.workswhale

object ContactStorage {
    private val totalContactList: ArrayList<Contact> = ArrayList()

    init {
        addDummyContact()
    }

    private fun addDummyContact() {

    }

    fun getTotalContactList(): ArrayList<Contact> = totalContactList

    fun addContact(contact: Contact) {
        totalContactList.add(contact)
    }
}