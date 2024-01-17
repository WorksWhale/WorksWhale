package com.example.workswhale

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workswhale.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    interface FragmentDataListener {
        fun onDataReceived(data: Contact.Person)
    }
    private var listener: FragmentDataListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)

        val dataList = mutableListOf<Contact>()

        with(binding) {
            rvContactlistList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvContactlistList.setHasFixedSize(true)
            rvContactlistList.adapter = ContactAdapter(ContactStorage.totalContactList).apply {
                itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View, position: Int) {

                        val bundle = Bundle() // 번들을 통해 값 전달
//                        val fragment2 = ContactDetailFragment.newInstance("${ContactStorage.totalContactList}")
                        val clickedItem = ContactStorage.totalContactList[position]
                        bundle.putParcelable(
                            "Contact.Person",
                            clickedItem
                        )
                      when (val item = ContactStorage.totalContactList[position]){
                            is Contact.Person ->  {
                                listener?.onDataReceived(item)
                                Log.d(TAG, "onClickItem: $item")
                                bundle.putParcelable(
                                    "Contact.Person",
                                    clickedItem
                                )
                                item.let {
                                    bundle.putString("name",it.name)
                                    bundle.putInt("profileImage", it.profileImage)
                                    bundle.putString("phoneNumber", it.phoneNumber)
                                    bundle.putInt("department", it.department)
                                    bundle.putString("email", it.email)
                                    bundle.putString("memo", it.memo)
                                }
                            }

                            else -> Unit
                        }
//                        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
                        val fragment1 = ContactDetailFragment()

                        fragment1.arguments = bundle

                        Log.d("Click", "ContactListFragment : $position")
                        Log.d(TAG, "onClickBundle: $bundle")
                    }
                }
            }
            rvContactlistList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactlistList, isHeader = { position: Int ->
                    ContactStorage.totalContactList[position] is Contact.Title
                }))

        return binding.root
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {
        fun newInstance() =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}