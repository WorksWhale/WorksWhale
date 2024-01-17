package com.example.workswhale

import android.content.ContentValues.TAG
import android.content.Context
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workswhale.databinding.FragmentContactListBinding
import java.util.Calendar

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

        with(binding) {
            rvContactlistList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            rvContactlistList.setHasFixedSize(true)
            val adapter = ContactAdapter(ContactStorage.totalContactList).apply {
                itemClick = object : ContactAdapter.ItemClick {
                    override fun onClick(view: View, position: Int) {

                        val bundle = Bundle() // 번들을 통해 값 전달
//                        val fragment2 = ContactDetailFragment.newInstance("${ContactStorage.totalContactList}")
                        val clickedItem = ContactStorage.totalContactList[position]
                      when (val item = ContactStorage.totalContactList[position]){
                            is Contact.Person ->  {
                                listener?.onDataReceived(item)
                                Log.d(TAG, "onClickItem: $item")
                                bundle.putParcelable(
                                    "Contact.Person",
                                    clickedItem
                                )
                            }
                            else -> Unit
                        }
                        Log.d("Click", "ContactListFragment : $position")
                        Log.d(TAG, "onClickBundle: $bundle")
                    }
                }
                itemLongClick =
                    object : ContactAdapter.ItemLongClick {
                        override fun onLongClick(view: View, position: Int) {
                            val builder = AlertDialog.Builder(requireActivity(),R.style.MyAlertDialogStyle)
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
            rvContactlistList.adapter = adapter
            rvContactlistList.addItemDecoration( // Sticky Header 구현을 위한
                HeaderItemDecoration(recyclerView = binding.rvContactlistList, isHeader = { position: Int ->
                    ContactStorage.totalContactList[position] is Contact.Title
                }))

            // 플로팅 버튼 클릭시, 새로운 사람 추가 기능 구현
            ftbtnContactlist.setOnClickListener {
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