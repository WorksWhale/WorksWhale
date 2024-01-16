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
        private const val VIEW_TYPE_PERSON = 2

    }
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

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
            is Contact.Person -> VIEW_TYPE_PERSON
        }
    }

    inner class TitleViewHolder(private val binding: ContactListTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Contact.Title) {
            binding.tvListtitle.setText("${item.department}(${ContactStorage.totalContactList.size})")
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