package com.example.workswhale.contactListFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.workswhale.ConstValues
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.R
import com.example.workswhale.databinding.ContactListPersonBinding
import com.example.workswhale.databinding.ContactListTitleBinding
import java.util.Collections

interface ContactItemClick {
    fun onClick(view: View?, data: Contact)
}

class ContactAdapter(val dataList : ArrayList<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() , Filterable{

    var itemClick: ContactItemClick? = null

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 위치에 해당하는 뷰타입 번호에 맞춰 뷰 생성(레이아웃)
        return when(viewType){
            ConstValues.VIEW_TYPE_TITLE -> {
                TitleViewHolder(ContactListTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> {
                PersonViewHolder(ContactListPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 각 뷰에 맞는 객체 데이터 바인딩
        when(filteredList[position]) {
            is Contact.Title -> (holder as TitleViewHolder).bind(filteredList[position] as Contact.Title)
            is Contact.Person -> (holder as PersonViewHolder).bind(filteredList[position] as Contact.Person)
        }

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, filteredList[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return when(filteredList[position]) {
             is Contact.Title -> ConstValues.VIEW_TYPE_TITLE
             is Contact.Person -> ConstValues.VIEW_TYPE_LIST
        }
    }

    inner class TitleViewHolder(private val binding: ContactListTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Contact.Title) {
            binding.tvContactListDepartmentTitle.setText(departmentList[item.department])
            binding.tvContactListDepartmentCount.text = "(${ContactStorage.countDepartment(item.department)})"
        }
    }

    inner class PersonViewHolder(private  val binding: ContactListPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Contact.Person) {
            with(binding) {
                if (ContactStorage.checkStartAlphabet(item.profileImage)) {
                    ivContactListPersonProfile.setImageURI(item.profileImage.toUri())
                } else {
                    ivContactListPersonProfile.setImageResource(item.profileImage.toInt())
                }
                tvContactListPersonName.text = item.name
                tvContactListPersonMemo.text = item.memo
                if (item.isLiked) {
                    ivContactListPersonFavorite.setImageResource(R.drawable.ic_main_fill_favorite)
                } else {
                    ivContactListPersonFavorite.setImageResource(R.drawable.ic_main_empty_favorite)
                }
            }
        }
    }

    // 리사이클러뷰 검색 기능 (필터)
    private var filteredList: ArrayList<Contact> = dataList
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if(charString.isBlank()) {
                    dataList
                } else {
                    val filteredList = dataList.filter {
                        it is Contact.Person && (it.name.contains(charString, true)
                                || it.phoneNumber.contains(charString, true))
                    }
                    filteredList as ArrayList<Contact>
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<Contact>
                notifyDataSetChanged()
            }
        }
    }

    // position 위치의 데이터를 삭제 후 어댑터 갱신
    fun removeData(position: Int, view : View){
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    // 현재 선택된 데이터와 드래그한 위치에 있는 데이터를 교환
    fun swapData(fromPos: Int, toPos: Int) {
        Collections.swap(dataList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }
}