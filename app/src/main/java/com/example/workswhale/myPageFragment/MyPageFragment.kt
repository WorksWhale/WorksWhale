package com.example.workswhale.myPageFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workswhale.databinding.FragmentMyPageBinding

class MyPageFragment(): Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun updateData(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        binding.ivMyPageProfileImage.setImageDrawable(profileImage)
        binding.tvMyPageName.text = name
        binding.tvDetailPhoneNumber.text = phoneNumber
        binding.tvDetailEmail.text = email
    }

    fun giveData(): List<String> {
        return listOf(
            binding.tvMyPageName.text.toString(),
            binding.tvDetailPhoneNumber.text.toString(),
            binding.tvDetailEmail.text.toString()
        )
    }

    fun giveImageData(): Drawable {
        return binding.ivMyPageProfileImage.drawable
    }
}