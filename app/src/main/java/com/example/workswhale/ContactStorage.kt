package com.example.workswhale

import android.net.Uri

object ContactStorage {
    var totalContactList: ArrayList<Contact> = ArrayList()

    init {
        addDummyContact()
    }

    private fun addDummyContact() {
        totalContactList.apply {
            add(Contact.Title(0))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_1"),"박소담","010-1212-1212",0,"abc@sparta.com","이제 막 입사한", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_2"),"정해인","010-1212-1212",0,"abc@sparta.com","신입사원 담당", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_3"),"이정민","010-1212-1212",0,"abc@sparta.com","근태관리", false, "OFF"))
            add(Contact.Title(1))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_4"),"민희정","010-3434-3434",1,"abc@sparta.com","말이 거의 없으신듯", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_5"),"마동석","010-3434-3434",1,"abc@sparta.com","주먹 잘 쓰시는", false, "OFF"))
            add(Contact.Title(2))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_6"),"송중기","010-5656-5656",2,"abc@sparta.com","밝게 잘 도와주심", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_7"),"박해일","010-5656-5656",2,"abc@sparta.com","무뚝뚝하지만, 도와줄건 도와주시는", false, "OFF"))
            add(Contact.Title(3))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_8"),"박보겸","010-7878-7878",3,"abc@sparta.com","영업 2등", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_9"),"유재석","010-7878-7878",3,"abc@sparta.com","영업 3등", false, "OFF"))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_10"),"장원영","010-7878-7878",3,"abc@sparta.com","영업 1등", false, "OFF"))
            add(Contact.Title(4))
            add(Contact.Person(Uri.parse("android.resource://com.example.workswhale/drawable/img_person_11"),"로날두","010-5542-7878",4,"abc@sparta.com","돈 많음", false, "OFF"))
            add(Contact.Title(5))
        }
    }

    fun addContact(contact: Contact) {
        when (contact) {
            is Contact.Person -> {
                when (contact.department) {
                    0 -> {
                        totalContactList.add(totalContactList.indexOf(Contact.Title(1)), contact)
                    }
                    1 -> {
                        totalContactList.add(totalContactList.indexOf(Contact.Title(2)), contact)
                    }
                    2 -> {
                        totalContactList.add(totalContactList.indexOf(Contact.Title(3)), contact)
                    }
                    3 -> {
                        totalContactList.add(totalContactList.indexOf(Contact.Title(4)), contact)
                    }
                    4 -> {
                        totalContactList.add(totalContactList.indexOf(Contact.Title(5)), contact)
                    }
                    5 -> {
                        totalContactList.add(contact)
                    }
                }
            }
            else -> Unit
        }
    }

    fun countDepartment(num: Int): Int {
        var count = 0
        for (item in totalContactList) {
            when (item) {
                is Contact.Person -> {
                    if (item.department == num) count++
                }
                else -> Unit
            }
        }

        return count
    }

    fun changeLiked(position: Int, item: Contact.Person) {
        when (totalContactList[position]) {
            is Contact.Person -> {
                val curLike = item.isLiked
                item.isLiked = !curLike
            }
            else -> Unit
        }
    }
}