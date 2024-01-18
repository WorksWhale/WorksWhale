package com.example.workswhale.contactDetailFragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.workswhale.Contact
import com.example.workswhale.ContactAdapter
import com.example.workswhale.ContactStorage
import com.example.workswhale.R
import com.example.workswhale.databinding.FragmentContactDetailBinding
import com.example.workswhale.mainActivity.MainActivity


class ContactDetailFragment : Fragment(), MainActivity.onBackPressedListener {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var isLiked = false
    private var receivedItem: Contact.Person? = null

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "arguments: $arguments")

        arguments?.let {
            receivedItem = it.getParcelable("contact")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)

        with(binding) {
            receivedItem?.let {
                if (ContactStorage.checkStartAlphabet(it.profileImage)) {
                    ivProfile.setImageURI(it.profileImage.toUri())
                } else {
                    ivProfile.setImageResource(it.profileImage.toInt())
                }
                tvDetailName.text = it.name
                tvDetailPhoneNumber.text = it.phoneNumber
                tvDetailEmail.text = it.email
                tvDetailMemo.text = it.memo
                isLiked = it.isLiked == true
            }

            tvDetailDepartment.text =
                requireContext().getString(departmentList[receivedItem!!.department])

            ivFavorite.setImageResource(
                if (isLiked) {
                    R.drawable.ic_contact_detail_fill_favorite
                } else {
                    R.drawable.ic_contact_detail_empty_favorite
                }
            )

            ivFavorite.setOnClickListener {
                val position = ContactStorage.totalContactList.indexOf(receivedItem as Contact)
                if (!isLiked) {
                    ivFavorite.setImageResource(R.drawable.ic_contact_detail_fill_favorite)
                    isLiked = true
                    ContactStorage.changeLiked(position)
                    Log.d(TAG, "ivFavoriteClicked: $isLiked")
                    Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")

                } else {
                    ivFavorite.setImageResource(R.drawable.ic_contact_detail_empty_favorite)
                    isLiked = false
                    ContactStorage.changeLiked(position)
                    Log.d(TAG, "ivFavoriteClicked: $isLiked")
                    Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")
                }
            }
            val phoneNumber =
                tvDetailPhoneNumber.text//phonNumber에는 010-1234-5678로 넣으면 01012345678로 변환됨



            tvMessage.setOnClickListener {
                val smsUri = Uri.parse("smsto:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(smsUri)
                intent.putExtra("sms_body", "") //해당 값에 전달하고자 하는 문자메시지 전달
                startActivity(intent)
            }

            tvCall.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(intent)
            }
        }
        return binding.root
    }

    companion object {
        fun newInstance(data: Contact.Person) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    Log.d(TAG, "newInstance: $arguments")
                    data.isLiked = isLiked
                    putParcelable("contact", data)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onBackPressed() {
//        val bundle = Bundle() // 번들을 통해 값 전달
//        val clickedItem = receivedItem
//        clickedItem!!.isLiked = isLiked
//        Log.d(TAG, "onBackPressed: $clickedItem")
//        Log.d(TAG, "onBackPressed: $isLiked")
//        bundle.putBoolean("isLiked", isLiked)
//        bundle.putParcelable(
//            "Contact.Person",
//            clickedItem
//        )
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        requireActivity().supportFragmentManager.popBackStack()
    }
}