package com.example.workswhale

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.workswhale.databinding.DialogAddContactBinding
import java.util.regex.Pattern

class AddContactDialog: DialogFragment() {

    interface OkClick {
        fun onClick()
    }

    var okClick: OkClick? = null
    private var _binding: DialogAddContactBinding? = null
    private val binding get() = _binding!!

    private val editTextList: List<EditText>
        get() = listOf(
            binding.etAddContactName,
            binding.etAddContactPhoneNumber,
            binding.etAddContactEmail,
            binding.etAddContactMemo
        )

    private val departmentList: List<Int>
        get() = listOf(
            R.string.human_resources_department,
            R.string.public_relations_department,
            R.string.research_development_department,
            R.string.planning_department,
            R.string.accounting_department,
            R.string.sales_department
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextChangedListener()
        setOnFocusChangedListener()
        setDepartmentSpinner()

        binding.btnAddContactCancel.setOnClickListener {
            dismiss()
        }

        binding.btnAddContactAdd.setOnClickListener {
            var department = 0
            departmentList.map { getString(it) }.forEachIndexed { idx, item ->
                if (binding.spinnerAddContact.selectedItem.toString() == item) department = idx}
            ContactStorage.addContact(Contact.Person(
                name = binding.etAddContactName.text.toString(),
                phoneNumber = binding.etAddContactPhoneNumber.text.toString(),
                department = department,
                email = binding.etAddContactEmail.text.toString(),
                memo = binding.etAddContactMemo.text.toString(),
                profileImage = R.drawable.person_1,
                isLiked = false
            ))
            okClick?.onClick()
            dismiss()
        }
    }

    private fun setTextChangedListener() {
        editTextList.forEach { editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
                setAddButtonEnable()
            }
        }
        binding.etAddContactPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun setOnFocusChangedListener() {
        editTextList.forEach { editText ->
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus.not()) {
                    editText.setErrorMessage()
                    setAddButtonEnable()
                }
            }
        }
    }

    private fun setDepartmentSpinner() {
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, departmentList)
        binding.spinnerAddContact.adapter = adapter
    }

    // EditText에서 에러 메세지를 출력하기 위해 만든 확장함수
    private fun EditText.setErrorMessage() {
        when (this) {
            binding.etAddContactName -> error = getMessageValidName()
            binding.etAddContactPhoneNumber -> error = getMessageValidPhoneNumber()
            binding.etAddContactEmail -> error = getMessageValidEmail()
            else -> Unit
        }
    }

    private fun String.startZeroOneZero() = this.length > 3 && this.substring(0..2) == "010"

    private fun String.checkEmailFormat(): Boolean {
        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, this)
    }

    // 이름에 대한 에러 메세지 반환하는 함수
    private fun getMessageValidName(): String? {
        val name = binding.etAddContactName.text.toString()
        return when {
            name.isBlank() -> AddContactErrorMessage.EMPTY_NAME
            else -> null
        }?.message?.let { getString(it) }
    }

    // 전화번호에 대한 에러 메세지 반환하는 함수
    private fun getMessageValidPhoneNumber(): String? {
        val phoneNumber = binding.etAddContactPhoneNumber.text.toString()
        return when {
            phoneNumber.isBlank() -> AddContactErrorMessage.EMPTY_PHONE_NUMBER
            phoneNumber.startZeroOneZero().not() -> AddContactErrorMessage.INVALID_PHONE_NUMBER
            else -> null
        }?.message?.let { getString(it) }
    }

    // 이메일에 대한 에러 메세지 반환하는 함수
    private fun getMessageValidEmail(): String? {
        val email = binding.etAddContactEmail.text.toString()
        return when {
            email.isBlank() -> AddContactErrorMessage.EMPTY_EMAIL
            email.checkEmailFormat().not() -> AddContactErrorMessage.INVALID_EMAIL
            else -> null
        }?.message?.let { getString(it) }
    }

    private fun setAddButtonEnable() {
        binding.btnAddContactAdd.isEnabled = getMessageValidName() == null
                && getMessageValidEmail() == null
                && getMessageValidPhoneNumber() == null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}