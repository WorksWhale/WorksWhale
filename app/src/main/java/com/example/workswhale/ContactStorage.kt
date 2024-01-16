package com.example.workswhale

object ContactStorage {
    val totalContactList: ArrayList<Contact> = ArrayList()

    init {
        addDummyContact()
    }

    private fun addDummyContact() {
       totalContactList.add(Contact.Title("인사부"))
        totalContactList.add(Contact.Person(R.drawable.person_1,"박소담 사원","010-1212-1212","인사부","abc@sparta.com","이제 막 입사한"))
        totalContactList.add(Contact.Person(R.drawable.person_2,"정해인 사원","010-1212-1212","인사부","abc@sparta.com","신입사원 담당"))
        totalContactList.add(Contact.Person(R.drawable.person_3,"이정민 대리","010-1212-1212","인사부","abc@sparta.com","근태관리"))
        totalContactList.add(Contact.Person(R.drawable.person_3,"이정민 대리","010-1212-1212","인사부","abc@sparta.com","근태관리"))
        totalContactList.add(Contact.Title("총무부"))
        totalContactList.add(Contact.Person(R.drawable.person_4,"민희정 대리","010-3434-3434","총무부","abc@sparta.com","말이 거의 없으신듯"))
        totalContactList.add(Contact.Person(R.drawable.person_5,"마동석 과장","010-3434-3434","총무부","abc@sparta.com","주먹 잘 쓰시는"))
        totalContactList.add(Contact.Title("재무부"))
        totalContactList.add(Contact.Person(R.drawable.person_6,"송중기 대리","010-5656-5656","재무부","abc@sparta.com","밝게 잘 도와주심"))
        totalContactList.add(Contact.Person(R.drawable.person_7,"박해일 과장","010-5656-5656","재무부","abc@sparta.com","무뚝뚝하지만, 도와줄건 도와주시는"))
        totalContactList.add(Contact.Person(R.drawable.person_7,"박해일 과장","010-5656-5656","재무부","abc@sparta.com","무뚝뚝하지만, 도와줄건 도와주시는"))
        totalContactList.add(Contact.Title("영업부"))
        totalContactList.add(Contact.Person(R.drawable.person_8,"박보겸 사원","010-7878-7878","영업부","abc@sparta.com","영업 2등"))
        totalContactList.add(Contact.Person(R.drawable.person_8,"박보겸 사원","010-7878-7878","영업부","abc@sparta.com","영업 2등"))
        totalContactList.add(Contact.Person(R.drawable.person_9,"유재석 부장","010-7878-7878","영업부","abc@sparta.com","영업 3등"))
        totalContactList.add(Contact.Person(R.drawable.person_9,"유재석 부장","010-7878-7878","영업부","abc@sparta.com","영업 3등"))
        totalContactList.add(Contact.Person(R.drawable.person_10,"장원영 대리","010-7878-7878","영업부","abc@sparta.com","영업 1등"))
        totalContactList.add(Contact.Person(R.drawable.person_10,"장원영 대리","010-7878-7878","영업부","abc@sparta.com","영업 1등"))

    }
    
    fun getTotalContactList(): ArrayList<Contact> = totalContactList

    fun addContact(contact: Contact) {
        totalContactList.add(contact)
    }
}