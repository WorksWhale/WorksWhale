package com.example.workswhale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workswhale.databinding.ContactListPersonBinding
import com.example.workswhale.databinding.ContactListTitleBinding

class ContactAdapter(val dataList : ArrayList<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        private const val VIEW_TYPE_TITLE = 1
        private const val VIEW_TYPE_LIST = 2
        private const val VIEW_TYPE_GRID = 3

    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    interface ItemLongClick {
        fun onLongClick(view : View, position : Int)
    }
    var itemClick: ItemClick? = null
    var itemLongClick : ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){ // 위치에 해당하는 뷰타입 번호에 맞춰 뷰 생성(레이아웃)
            VIEW_TYPE_TITLE -> {
                TitleViewHolder(ContactListTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> {
                PersonViewHolder(ContactListPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = dataList[position]) { // 각 뷰에 맞는 객체 데이터 바인딩
            is Contact.Title -> (holder as TitleViewHolder).bind(dataList[position] as Contact.Title)
            is Contact.Person -> (holder as PersonViewHolder).bind(dataList[position] as Contact.Person)
        }

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener{
            itemLongClick?.onLongClick(it,position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) { // 각 뷰타입 확인 및 번호 부여 (구분)
            is Contact.Title -> VIEW_TYPE_TITLE
            is Contact.Person -> VIEW_TYPE_LIST
        }
    }

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )
    inner class TitleViewHolder(private val binding: ContactListTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Contact.Title) {
            binding.tvListtitle.setText(departmentList[item.department])
            binding.tvDepartmentCount.setText("(${ContactStorage.countDepartment(item.department)})")
        }
    }

    inner class PersonViewHolder(private  val binding: ContactListPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Contact.Person) {
            with(binding) {
             ivContactlistProfile.setImageResource(item.profileImage)
                tvContactlistName.setText(item.name)
                tvContactlistMemo.setText(item.memo)
            }
        }
    }


}