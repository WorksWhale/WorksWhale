package com.example.workswhale.contactDetailFragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.IntentKeys
import com.example.workswhale.R
import com.example.workswhale.databinding.FragmentContactDetailBinding

interface UpdateLike {
    fun update(position: Int)
}

class ContactDetailFragment : Fragment() {

    private var updateLike: UpdateLike? = null

    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var callback: OnBackPressedCallback

    private var isLiked: Boolean? = null
    private var receivedItem: Contact.Person? = null
    private var position = 0

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UpdateLike) {
            updateLike = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                arguments?.getInt(IntentKeys.EXTRA_POSITION)?.let { updateLike?.update(it) }
                requireActivity().supportFragmentManager.beginTransaction().remove(this@ContactDetailFragment).commit()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            receivedItem = it.getParcelable(IntentKeys.EXTRA_CONTACT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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
                tvDetailAlarm.text = it.alarm
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
                position = ContactStorage.totalContactList.indexOf(receivedItem as Contact)
                if (isLiked == false) {
                    ivDetailFavorite.setImageResource(R.drawable.ic_contact_detail_fill_favorite)
                    isLiked = true

                    changeLike(position, receivedItem!!)

                } else {
                    ivDetailFavorite.setImageResource(R.drawable.ic_contact_detail_empty_favorite)
                    isLiked = false
                    changeLike(position, receivedItem!!)
                }
            }

            //phoneNumber에는 010-1234-5678로 넣으면 01012345678로 변환됨
            val phoneNumber =
                tvDetailPhoneNumber.text

            tvDetailMessage.setOnClickListener {
                val smsUri = Uri.parse("smsto:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(smsUri)
                intent.putExtra(IntentKeys.EXTRA_SMS_BODY, "") //해당 값에 전달하고자 하는 문자메시지 전달
                startActivity(intent)
            }

            tvDetailCall.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(intent)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                arguments?.getInt(IntentKeys.EXTRA_POSITION)?.let { updateLike?.update(it) }
                requireActivity().supportFragmentManager.beginTransaction().remove(this@ContactDetailFragment).commit()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(data: Contact.Person) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IntentKeys.EXTRA_CONTACT, data)
                }
            }
    }

    private fun changeLike(position: Int, receivedItem: Contact.Person) {
        ContactStorage.changeLiked(position, receivedItem)
    }
}