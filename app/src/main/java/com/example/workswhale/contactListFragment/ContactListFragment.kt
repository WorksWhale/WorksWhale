package com.example.workswhale.contactListFragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.IntentKeys
import com.example.workswhale.R
import com.example.workswhale.addContactDialog.AddContactDialog
import com.example.workswhale.addContactDialog.AddContactDialogOkClick
import com.example.workswhale.databinding.FragmentContactListBinding
import java.util.Calendar

interface FragmentDataListener {
    fun onDataReceived(data: Contact.Person)
}

class ContactListFragment : Fragment() {

    private var listener: FragmentDataListener? = null

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    val adapter = ContactAdapter(ContactStorage.totalContactList)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val bundle = Bundle() // 번들을 통해 값 전달

        with(binding) {
            rvContactList.adapter = adapter
            rvContactList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            rvContactList.setHasFixedSize(true)
                //리스트에서 클릭한 아이템의 데이터를 ContactDetailFragment에 전달
                adapter.apply {
                itemClick = object : ContactItemClick {
                    override fun onClick(view: View?, data: Contact) {
                        val clickedItem = data
                        when (val item = data){
                            is Contact.Person ->  {
                                listener?.onDataReceived(item)
                                bundle.putParcelable(
                                    IntentKeys.EXTRA_CONTACT_PERSON,
                                    clickedItem
                                )
                            }
                            else -> Unit
                        }
                        requireActivity().supportFragmentManager.beginTransaction().remove(ContactListFragment()).commit()
                    }
                }
            }

            rvContactList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactList, isHeader = { position: Int ->
                    ContactStorage.totalContactList[position] is Contact.Title
                })
            )
            adapter.notifyDataSetChanged()

            // 플로팅 버튼 클릭시, 새로운 사람 추가 기능 구현
            fabContactList.setOnClickListener {
                val dialog = AddContactDialog()
                dialog.okClick = object: AddContactDialogOkClick {
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
            val searchViewTextListener: SearchView.OnQueryTextListener =
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
            ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(rvContactList)

//            // 구분선 추가
//            binding.rvContactlistList.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

            // 다른 곳 터치 시 기존 선택했던 뷰 닫기
            rvContactList.setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(rvContactList)
                false
            }

            return binding.root
        }
    }

    private fun setAlarm(name: String, second: Int) {
        if (second == 0) return

        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java).apply {
            putExtra(IntentKeys.EXTRA_NAME, name)
        }
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, second)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Toast.makeText(requireContext(),
            getString(R.string.toast_message_make_notification, name), Toast.LENGTH_SHORT).show()
    }

    // 리스트에서 isLiked가 변경된 데이터가 있으면 해당 데이터만 갱신하고 아이콘 변경
    fun updateLike(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}