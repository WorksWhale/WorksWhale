package com.example.workswhale.myPageFragment

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import com.example.workswhale.databinding.FragmentMyPageBinding
import java.io.ByteArrayOutputStream

class MyPageFragment(): Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val sp by lazy {
        requireContext().getSharedPreferences("sp", MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserProfile()
    }

    fun updateData(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        binding.ivMyPageProfileImage.setImageDrawable(profileImage)
        binding.tvMyPageName.text = name
        binding.tvDetailPhoneNumber.text = phoneNumber
        binding.tvDetailEmail.text = email

        saveUserProfile(profileImage, name, phoneNumber, email)
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

    private fun saveUserProfile(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        val stream = ByteArrayOutputStream()
        profileImage.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream) // Drawable을 Bitmap으로 변환
        val byteArray = stream.toByteArray()
        val editor = sp.edit()
        editor.putString("storeProfileImage", Base64.encodeToString(byteArray, Base64.DEFAULT)) // Bitmap을 Base64 문자열로 인코딩
        editor.putString("storeName", name)
        editor.putString("storePhoneNumber", phoneNumber)
        editor.putString("storeEmail", email)
        editor.apply()
    }

    private fun loadUserProfile() {
        val storedProfileImage = sp.getString("storeProfileImage", "")
        if (storedProfileImage != "") {
            val byteArray = Base64.decode(storedProfileImage, Base64.DEFAULT) // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            binding.ivMyPageProfileImage.setImageBitmap(bitmap) // Bitmap을 이용해 이미지 띄우기
            binding.tvMyPageName.text = sp.getString("storeName", "")
            binding.tvDetailPhoneNumber.text = sp.getString("storePhoneNumber", "")
            binding.tvDetailEmail.text = sp.getString("storeEmail", "")
        }
    }
}