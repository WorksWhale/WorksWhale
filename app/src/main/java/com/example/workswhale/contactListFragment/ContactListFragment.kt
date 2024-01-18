package com.example.workswhale.contactListFragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.R
import com.example.workswhale.SwipeHelperCallback
import com.example.workswhale.addContactDialog.AddContactDialog
import com.example.workswhale.databinding.FragmentContactListBinding
import java.util.Calendar

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private var receivedItem: Contact.Person? = null

    val adapter = ContactAdapter(ContactStorage.totalContactList)

    interface FragmentDataListener {
        fun onDataReceived(data: Contact.Person)
    }
    private var listener: FragmentDataListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            receivedItem = it.getParcelable("contact")
            Log.d(TAG, "onCreateReceivedItem: $receivedItem")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val bundle = Bundle() // 번들을 통해 값 전달
        Log.d(TAG, "onCreateView: $bundle")
        with(binding) {
            rvContactList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            rvContactList.setHasFixedSize(true)
//            val adapter = ContactAdapter(ContactStorage.totalContactList)
                adapter.apply {
                itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View?, data: Contact) {
//                        val fragment2 = ContactDetailFragment.newInstance("${ContactStorage.totalContactList}")
                        val clickedItem = data
                        when (val item = data){
                            is Contact.Person ->  {
                                Log.d(TAG, "position: $data")
                                listener?.onDataReceived(item)
                                Log.d(TAG, "onClickItem: $item")
                                bundle.putParcelable(
                                    "Contact.Person",
                                    clickedItem
                                )
                            }
                            else -> Unit
                        }
                        Log.d("Click", "ContactListFragment : $data")
                        Log.d(TAG, "onClickBundle: $bundle")
                        requireActivity().supportFragmentManager.beginTransaction().remove(ContactListFragment()).commit()
                        Log.i(TAG, "onClick: ContactListFragment")
                        onPause()
                    }
                }
                itemLongClick =
                    object : ContactAdapter.ItemLongClick {
                        override fun onLongClick(view: View, position: Int) {
                            val builder = AlertDialog.Builder(requireActivity(),
                                R.style.MyAlertDialogStyle
                            )
                            builder.setTitle("목록 삭제")
                            builder.setMessage("정말로 삭제하시겠습니까?")
                            builder.setIcon(R.drawable.ic_logo_white)
                            builder.setCancelable(false)
                            val listener = object : DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    when(which) {
                                        DialogInterface.BUTTON_POSITIVE -> {
                                            ContactStorage.totalContactList.removeAt(position)
                                            notifyItemRemoved(position)
                                            notifyDataSetChanged()
                                        Toast.makeText(context,"삭제되었습니다",Toast.LENGTH_SHORT).show()}
                                        DialogInterface.BUTTON_NEGATIVE -> {
                                            dialog?.dismiss()
                                            Toast.makeText(context,"취소하였습니다",Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                            builder.setPositiveButton("삭제", listener)
                            builder.setNegativeButton("취소", listener)
                            builder.show()
                        }
                    }
            }
            rvContactList.adapter = adapter
            rvContactList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactList, isHeader = { position: Int ->
                    ContactStorage.totalContactList[position] is Contact.Title
                })
            )
            adapter.notifyDataSetChanged()

            // 플로팅 버튼 클릭시, 새로운 사람 추가 기능 구현
            fabContactList.setOnClickListener {
                val dialog = AddContactDialog()
                dialog.okClick = object: AddContactDialog.OkClick {
                    override fun onClick(name: String, second: Int) {
                        adapter.notifyDataSetChanged()
                        setAlarm(name, second)
                    }
                }
                dialog.show(
                    requireActivity().supportFragmentManager, "AddContactDialog"
                )
            }
            // 목록 검색 기능
            var searchViewTextListener: SearchView.OnQueryTextListener =
                object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
                    override fun onQueryTextSubmit(s: String): Boolean {
                        return false
                    }
                    //텍스트 입력/수정시에 호출
                    override fun onQueryTextChange(s: String): Boolean {
                        adapter.filter.filter(s)
                        return false
                    }
                }
            svContactListSearch.setOnQueryTextListener(searchViewTextListener)

            // 리사이클러뷰에 스와이프, 드래그 기능 달기
            val swipeHelperCallback = SwipeHelperCallback(adapter).apply {
                // 스와이프한 뒤 고정시킬 위치 지정
                setClamp(resources.displayMetrics.widthPixels.toFloat() / 4)    // 1080 / 4 = 270
            }
            ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvContactList)

//            // 구분선 추가
//            binding.rvContactlistList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

            // 다른 곳 터치 시 기존 선택했던 뷰 닫기
            binding.rvContactList.setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(binding.rvContactList)
                false
            }

            return binding.root

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FragmentLifeCycle", "List_onAttach()")
        if (context is FragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

    private fun setAlarm(name: String, second: Int) {
        if (second == 0) return

        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java).apply {
            putExtra("name", name)
        }
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, second)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Toast.makeText(requireContext(), "${name}님에 대한 연락 알람이 설정되었습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(data: Contact.Person) =
            ContactListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("contact", data)
                }
            }
    }

    fun updateLike(position: Int) {
        adapter.notifyItemChanged(position)
    }
}