package com.example.workswhale

data class Contact(
    val profileImage: Int = R.drawable.img_default_profile,
    val name: String,
    val phoneNumber: String,
    val department: String,
    val email: String,
    val memo: String,

    )