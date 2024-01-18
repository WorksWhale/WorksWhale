package com.example.workswhale.mainActivity

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.workswhale.myPageFragment.MyPageFragment
import com.example.workswhale.contactListFragment.ContactListFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val myPageFragment = MyPageFragment()
    private val contactListFragment = ContactListFragment()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> contactListFragment
            else -> myPageFragment
        }
    }

    fun editInfo(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        // 프래그먼트 함수 생성
        myPageFragment.updateData(profileImage, name, phoneNumber, email)
    }

    fun getInfo(): List<String> {
        return myPageFragment.giveData()
    }

    fun getImageInfo(): Drawable {
        return myPageFragment.giveImageData()
    }
}