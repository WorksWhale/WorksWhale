package com.example.workswhale

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        with(binding) {
            rvContactlistList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvContactlistList.setHasFixedSize(true)
            val adapter = ContactAdapter(ContactStorage.totalContactList).apply {
                itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View, position: Int) {








                        Log.d("Click", "ContactListFragment : $position")
                    }
                }
                itemLongClick =
                    object : ContactAdapter.ItemLongClick {
                        override fun onLongClick(view: View, position: Int) {
                            val builder = AlertDialog.Builder(requireActivity())
                            builder.setTitle("목록 삭제")
                            builder.setMessage("정말로 삭제하시겠습니까?")
                            builder.setIcon(R.drawable.ic_longclick_remove)
                            val listener = object : DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    when(which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            ContactStorage.totalContactList.removeAt(position)
                                            notifyItemRemoved(position)
                                            notifyDataSetChanged()}
                                        DialogInterface.BUTTON_NEGATIVE -> dialog?.dismiss()
                                    }
                                }
                            }
                            builder.setPositiveButton("확인", listener)
                            builder.setNegativeButton("취소", listener)
                            builder.show()
                        }
                    }
            }
            rvContactlistList.adapter = adapter
            rvContactlistList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactlistList, isHeader = { position: Int ->
                    ContactStorage.totalContactList[position] is Contact.Title
                }))

            // 플로팅 버튼 클릭시, 새로운 사람 추가 기능 구현
            ftbtnContactlist.setOnClickListener {
                val dialog = AddContactDialog()
                dialog.okClick = object: AddContactDialog.OkClick {
                    override fun onClick() {
                        // 리사이클러뷰 아이템 업데이트하기
                        adapter.notifyDataSetChanged()
                        Toast.makeText(requireContext(), "연락처가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.show(
                    requireActivity().supportFragmentManager, "AddContactDialog"
                )
            }

        return binding.root
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