package com.example.workswhale.contactDetailFragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
                arguments?.getInt(IntentKeys.EXTRA_POSITION, position)?.let { updateLike?.update(it) }
                requireActivity().supportFragmentManager.beginTransaction().remove(this@ContactDetailFragment).commit()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ContactListFragment에서 전달받은 데이터를 receivedItem에 저장
        arguments?.let {
            receivedItem = it.getParcelable(IntentKeys.EXTRA_CONTACT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactDetailBinding.inflate(inflater, container, false)

        //with로 묶어서 바인딩 처리
        with(binding) {
            receivedItem?.let {
                if (it.profileImage == null) {
                    ivDetailProfile.setImageResource(R.drawable.img_default_profile)
                } else {
                    ivDetailProfile.setImageURI(it.profileImage)
                }
                tvDetailName.text = it.name
                tvDetailPhoneNumber.text = it.phoneNumber
                tvDetailEmail.text = it.email
                tvDetailMemo.text = it.memo
                tvDetailAlarm.text = it.alarm
                isLiked = it.isLiked
            }

            // 부서 값이 Int형이므로 departmentList의 인덱스로 넣어 String 값 반환
            tvDetailDepartment.text =
                requireContext().getString(departmentList[receivedItem!!.department])

            // 가져온 데이터에서 isLiked 값에 따라 아이콘 변경
            ivDetailFavorite.setImageResource(
                if (isLiked == true) {
                    R.drawable.ic_contact_detail_fill_favorite
                } else {
                    R.drawable.ic_contact_detail_empty_favorite
                }
            )

            // 좋아요 아이콘 클릭 시 아이콘 상태에 따라 아이콘 변경 및 isLiked 값 변경
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

            // phoneNumber에 들어간 번호로 메시지 대화 시작
            tvDetailMessage.setOnClickListener {
                val smsUri = Uri.parse("smsto:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(smsUri)
                intent.putExtra(IntentKeys.EXTRA_SMS_BODY, "") //해당 값에 전달하고자 하는 문자메시지 전달
                startActivity(intent)
            }

            // phoneNumber를 가지고 다이얼이 들어간 전화 앱을 실행
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
            //상세정보에서 뒤로가기를 눌렀을 경우 변경된 값을 arguments를 통해서 전달
            override fun handleOnBackPressed() {
                arguments?.getInt(IntentKeys.EXTRA_POSITION, position)?.let { updateLike?.update(it) }
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

    // 상세정보에 있는 데이터를 totalContactList와 비교하여 isLiked 값을 변경
    private fun changeLike(position: Int, receivedItem: Contact.Person) {
        ContactStorage.changeLiked(position, receivedItem)
    }
}