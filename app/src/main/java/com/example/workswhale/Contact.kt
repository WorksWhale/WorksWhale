package com.example.workswhale

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
sealed class Contact : Parcelable {
    data class Title(val department: String) : Contact()
    data class Person(val profileImage: Int,
                      val name: String,
                      val phoneNumber: String,
                      val department: String,
                      val email: String,
                      val memo: String) : Contact()
}