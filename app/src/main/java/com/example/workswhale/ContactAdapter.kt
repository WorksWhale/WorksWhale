package com.example.workswhale

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.workswhale.databinding.ContactListPersonBinding
import com.example.workswhale.databinding.ContactListTitleBinding

class ContactAdapter(val dataList : ArrayList<Contact>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() , Filterable {
    companion object {
        private const val VIEW_TYPE_TITLE = 1
        private const val VIEW_TYPE_LIST = 2

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
        when(dataList[position]) { // 각 뷰에 맞는 객체 데이터 바인딩
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
        return when(dataList[position]) {
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

    var TAG = "ContactAdapter"
    var filteredPersons = ArrayList<Contact>()
    var itemFilter = ItemFilter()

    init {
        filteredPersons.addAll(dataList)
    }
    inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterString = constraint.toString()
            val results = FilterResults()

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Contact> = ArrayList<Contact>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = dataList
                results.count = dataList.size

                return results
                //공백제외 2글자 이하인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (person in dataList) {
                    when(person) {
                        is Contact.Person -> if (person.name.contains(filterString)) {
                            filteredList.add(person)
                    }
                        else -> Unit
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            } else {
                for (person in dataList) {
                    when(person) {
                        is Contact.Person -> if (person.name.contains(filterString) || person.phoneNumber.contains(filterString)) {
                            filteredList.add(person)
                    }
                        else -> Unit
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredPersons.clear()
            filteredPersons.addAll(results?.values as ArrayList<Contact>)
            notifyDataSetChanged()
        }
    }
    override fun getFilter(): Filter {
        return itemFilter
    }
}