package com.example.workswhale.contactDetailFragment

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.R
import com.example.workswhale.databinding.FragmentContactDetailBinding

interface UpdateLike {
    fun update(position: Int)
}

class ContactDetailFragment : Fragment() {

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!
    private var isLiked: Boolean? = null
    private var receivedItem: Contact.Person? = null
    private var position = 0

    private var updateLike: UpdateLike? = null

    private lateinit var callback: OnBackPressedCallback

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
            Log.d(TAG, "onCreateReceivedItem: $receivedItem")
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
                    ivDetailProfile.setImageURI(it.profileImage.toUri())
                } else {
                    ivDetailProfile.setImageResource(it.profileImage.toInt())
                }
                tvDetailName.text = it.name
                tvDetailPhoneNumber.text = it.phoneNumber
                tvDetailEmail.text = it.email
                tvDetailMemo.text = it.memo
                isLiked = it.isLiked
            }

            tvDetailDepartment.text =
                requireContext().getString(departmentList[receivedItem!!.department])

            ivDetailFavorite.setImageResource(
                if (isLiked == true) {
                    R.drawable.ic_contact_detail_fill_favorite
                } else {
                    R.drawable.ic_contact_detail_empty_favorite
                }
            )

            ivDetailFavorite.setOnClickListener {
                val position = ContactStorage.totalContactList.indexOf(receivedItem as Contact)
                if (!isLiked!!) {
                    ivDetailFavorite.setImageResource(R.drawable.ic_contact_detail_fill_favorite)
                    isLiked = true
                    changeLike()
                    Log.d(TAG, "ivFavoriteClicked: $isLiked")
                    Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")

                } else {
                    ivDetailFavorite.setImageResource(R.drawable.ic_contact_detail_empty_favorite)
                    isLiked = false
                    changeLike()
                    Log.d(TAG, "ivFavoriteClicked: $isLiked")
                    Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")
                }
            }
            val phoneNumber =
                tvDetailPhoneNumber.text//phonNumber에는 010-1234-5678로 넣으면 01012345678로 변환됨



            tvDetailMessage.setOnClickListener {
                val smsUri = Uri.parse("smsto:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(smsUri)
                intent.putExtra("sms_body", "") //해당 값에 전달하고자 하는 문자메시지 전달
                startActivity(intent)
            }

            tvDetailCall.setOnClickListener {
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
//                    data.isLiked = isLiked
                    putParcelable("contact", data)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun changeLike() {
        ContactStorage.changeLiked(position)
    }
    override fun onAttach(context: Context) {
        Log.d("FragmentLifeCycle", "Detail_onAttach()")
        super.onAttach(context)
        if (context is UpdateLike) {
            updateLike = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                arguments?.getInt("position")?.let { updateLike?.update(it) }
                requireActivity().supportFragmentManager.beginTransaction().remove(this@ContactDetailFragment).commit()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}