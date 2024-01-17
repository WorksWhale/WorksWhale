package com.example.workswhale

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
sealed class Contact : Parcelable {
    data class Title(val department: Int) : Contact()
    data class Person(val profileImage: Int,
                      val name: String,
                      val phoneNumber: String,
                      val department: Int,
                      val email: String,
                      val memo: String,
<<<<<<< HEAD
                      var isLiked: Boolean) : Contact()

=======
                      val alarm: String) : Contact()
>>>>>>> c6cf1f61b9a06926f51cc29adc8b0cf935856056
}