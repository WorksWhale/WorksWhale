package com.example.workswhale

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.workswhale.databinding.FragmentContactDetailBinding


class ContactDetailFragment : Fragment(), MainActivity.onBackPressedListener {

    private var binding: FragmentContactDetailBinding? = null
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var isLiked = false
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "arguments: $arguments")

        arguments?.let {
            receivedItem = it.getParcelable("contact")
            position = it.getInt("position")
            Log.d(TAG, "onCreateReceivedItem: $receivedItem")
            Log.d(TAG, "position: $position")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)

        receivedItem?.let {
            binding!!.ivProfile.setImageResource(it.profileImage)
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
                ContactStorage.changeLiked(position)
                Log.d(TAG, "ivFavoriteClicked: $isLiked")
                Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")

            } else {
                binding!!.ivFavorite.setImageResource(R.drawable.ic_empty_favorite)
                isLiked = false
                ContactStorage.changeLiked(position)
                Log.d(TAG, "ivFavoriteClicked: $isLiked")
                Log.d(TAG, "dataChanged: ${ContactStorage.totalContactList[position]}")
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
        fun newInstance(data: Contact.Person, position: Int) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    Log.d(TAG, "newInstance: $arguments")
                    data.isLiked = isLiked
                    putParcelable("contact", data)
                    putInt("position", position)
                }
            }
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
        val adapter = ContactAdapter(ContactStorage.totalContactList)
        adapter.notifyItemChanged(position)
        adapter.notifyDataSetChanged()
        val listFragment = ContactListFragment.newInstance()

        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frameLayout, listFragment)

//        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
//        requireActivity().supportFragmentManager.popBackStack()
    }
}