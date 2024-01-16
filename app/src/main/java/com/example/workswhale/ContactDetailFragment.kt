package com.example.workswhale

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.workswhale.databinding.FragmentContactDetailBinding


class ContactDetailFragment : Fragment() {

    private var binding: FragmentContactDetailBinding? = null
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

//    private val receivedItem: Contact?
//        get() = intent.getParcelableExtra("Contact")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContactDetailBinding.inflate(inflater, container, false)

        val phoneNumber = binding!!.tvDetailPhoneNumber.text//phonNumber에는 010-1234-5678로 넣으면 01012345678로 변환됨

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
        fun newInstance(param1: String) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}