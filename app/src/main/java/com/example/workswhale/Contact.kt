package com.example.workswhale

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
sealed class Contact : Parcelable {
    data class Title(val department: Int) : Contact()
    data class Person(
        val profileImage: Uri? = null,
        val name: String,
        val phoneNumber: String,
        val department: Int,
        val email: String,
        val memo: String,
        var isLiked: Boolean,
        val alarm: String) : Contact()

}