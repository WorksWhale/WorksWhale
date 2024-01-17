package com.example.workswhale

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
import com.example.workswhale.databinding.FragmentContactDetailBinding


class ContactDetailFragment : Fragment() {

    private var binding: FragmentContactDetailBinding? = null
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var isLiked = false

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )

    private var receivedItem: Contact.Person? = null

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
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)

        receivedItem?.let {
            if (ContactStorage.checkStartAlphabet(it.profileImage)) {
                binding!!.ivProfile.setImageURI(it.profileImage.toUri())
            } else {
                binding!!.ivProfile.setImageResource(it.profileImage.toInt())
            }
            binding!!.tvDetailName.text = it.name
            binding!!.tvDetailPhoneNumber.text = it.phoneNumber
            binding!!.tvDetailEmail.text = it.email
            binding!!.tvDetailMemo.text = it.memo
            isLiked = it.isLiked == true
        }

        binding!!.tvDetailDepartment.text =
            requireContext().getString(departmentList[receivedItem!!.department])

        binding!!.ivFavorite.setImageResource(
            if (isLiked) {
                R.drawable.ic_fill_favorite
            } else {
                R.drawable.ic_empty_favorite
            }
        )

        binding!!.ivFavorite.setOnClickListener {
            if (!isLiked) {
                binding!!.ivFavorite.setImageResource(R.drawable.ic_fill_favorite)
                isLiked = true
            } else {
                binding!!.ivFavorite.setImageResource(R.drawable.ic_empty_favorite)
                isLiked = false
            }
        }
        val phoneNumber =
            binding!!.tvDetailPhoneNumber.text//phonNumber에는 010-1234-5678로 넣으면 01012345678로 변환됨

        with(binding) {

            this!!.tvMessage.setOnClickListener {
                val smsUri = Uri.parse("smsto:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(smsUri)
                intent.putExtra("sms_body", "") //해당 값에 전달하고자 하는 문자메시지 전달
                startActivity(intent)
            }

            this.tvCall.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(intent)
            }
        }

        return binding?.root
    }

    companion object {
        fun newInstance(data: Contact.Person) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    Log.d(TAG, "newInstance: $arguments")
                    putParcelable("contact", data)
                }
            }
    }
}