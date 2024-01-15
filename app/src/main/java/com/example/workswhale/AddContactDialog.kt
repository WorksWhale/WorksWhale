package com.example.workswhale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.workswhale.databinding.DialogAddContactBinding

class AddContactDialog: DialogFragment() {
    private var _binding: DialogAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddContactBinding.inflate(inflater, container, false)

        binding.btnAddContactCancel.setOnClickListener {
            dismiss()
        }

        binding.btnAddContactAdd.setOnClickListener {
            // 입력된 정보를 DB에 저장
            dismiss()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}