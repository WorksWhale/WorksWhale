package com.example.workswhale

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val myPageFragment = MyPageFragment()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactListFragment()
            else -> myPageFragment
        }
    }

    fun editInfo(name: String, phoneNumber: String, email: String) {
        // 프래그먼트 함수 생성
        myPageFragment.updateData(name, phoneNumber, email)
    }
}