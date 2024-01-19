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
import com.example.workswhale.IntentKeys
import com.example.workswhale.databinding.FragmentMyPageBinding
import java.io.ByteArrayOutputStream

class MyPageFragment: Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private val sharedPreferences by lazy {
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
        with(binding) {
            ivMyPageProfileImage.setImageDrawable(profileImage)
            tvMyPageName.text = name
            tvMyPagePhoneNumber.text = phoneNumber
            tvMyPageEmail.text = email
        }

            saveUserProfile(profileImage, name, phoneNumber, email)
        }

    fun giveData(): List<String> {
        return listOf(
            binding.tvMyPageName.text.toString(),
            binding.tvMyPagePhoneNumber.text.toString(),
            binding.tvMyPageEmail.text.toString()
        )
    }

    fun giveImageData(): Drawable {
        return binding.ivMyPageProfileImage.drawable
    }

    private fun saveUserProfile(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        val stream = ByteArrayOutputStream()
        profileImage.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream) // Drawable을 Bitmap으로 변환
        val byteArray = stream.toByteArray()
        val editor = sharedPreferences.edit()
        editor.putString(IntentKeys.EXTRA_STORE_PROFILE_IMAGE, Base64.encodeToString(byteArray, Base64.DEFAULT)) // Bitmap을 Base64 문자열로 인코딩
        editor.putString(IntentKeys.EXTRA_STORE_NAME, name)
        editor.putString(IntentKeys.EXTRA_STORE_PHONE_NUMBER, phoneNumber)
        editor.putString(IntentKeys.EXTRA_STORE_EMAIL, email)
        editor.apply()
    }

    private fun loadUserProfile() {
        val storedProfileImage = sharedPreferences.getString(IntentKeys.EXTRA_STORE_PROFILE_IMAGE, "")
        if (storedProfileImage != "") {
            val byteArray = Base64.decode(storedProfileImage, Base64.DEFAULT) // Base64 문자열을 Bitmap으로 디코딩
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            with(binding) {
                ivMyPageProfileImage.setImageBitmap(bitmap) // Bitmap을 이용해 이미지 띄우기
                tvMyPageName.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_NAME, "")
                tvMyPagePhoneNumber.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_PHONE_NUMBER, "")
                tvMyPageEmail.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_EMAIL, "")
            }
        }
    }
}