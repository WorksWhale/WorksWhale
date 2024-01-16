package com.example.workswhale

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workswhale.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataList = mutableListOf<Contact>()

        with(binding) {
            rvContactlistList.layoutManager = LinearLayoutManager(this@ContactListFragment, LinearLayoutManager.VERTICAL, false)
            rvContactlistList.setHasFixedSize(true)
            rvContactlistList.adapter = ContactAdapter(dataList).apply {
                itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View, position: Int) {

//                        val fragment2 = ContactDetailFragment.newInstance()

//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id., fragment2)
//                            .addToBackStack(null)
//                            .commit()

                        Log.d("Click", "ContactListFragment : $position")
                    }
                }
            }
            rvContactlistList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactlistList, isHeader = { position: Int ->
                    dataList[position] is Contact.Title
                }))
        }
    }

    companion object {
        fun newInstance() =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}