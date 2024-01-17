package com.example.workswhale.addContactDialog

import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.workswhale.Contact
import com.example.workswhale.ContactStorage
import com.example.workswhale.R
import com.example.workswhale.databinding.DialogAddContactBinding
import java.util.regex.Pattern

class AddContactDialog: DialogFragment() {

    interface OkClick {
        fun onClick(name: String, second: Int)
    }

    var okClick: OkClick? = null
    private var _binding: DialogAddContactBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.ivAddContactDefaultImage.setImageURI(uri)
            binding.ivAddContactDefaultImage.scaleType = ImageView.ScaleType.CENTER_CROP
            imageUri = uri
        }
    }

    private val editTextList: List<EditText>
        get() = listOf(
            binding.etAddContactName,
            binding.etAddContactPhoneNumber,
            binding.etAddContactEmail,
            binding.etAddContactMemo
        )

    private val alarmLinearLayoutList: List<LinearLayout>
        get() = listOf(
            binding.linearLayoutAddContactAlarmBtn1,
            binding.linearLayoutAddContactAlarmBtn2,
            binding.linearLayoutAddContactAlarmBtn3,
            binding.linearLayoutAddContactAlarmBtn4,
            binding.linearLayoutAddContactAlarmBtn5
        )

    private val alarmTextViewList: List<TextView>
        get() = listOf(
            binding.tvAddContactAlarmBtn1,
            binding.tvAddContactAlarmBtn2,
            binding.tvAddContactAlarmBtn3,
            binding.tvAddContactAlarmBtn4,
            binding.tvAddContactAlarmBtn5
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

    private val alarmTimeList: List<Int>
        get() = listOf(
            R.string.alarm_time_off,
            R.string.alarm_time_five_seconds,
            R.string.alarm_time_five_minutes,
            R.string.alarm_time_ten_minutes,
            R.string.alarm_time_thirty_minutes
        )

    private var selectedTime = 0
    private var timeString = ""

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
            ContactStorage.addContact(
                Contact.Person(
                    name = binding.etAddContactName.text.toString(),
                    phoneNumber = binding.etAddContactPhoneNumber.text.toString(),
                    department = department,
                    email = binding.etAddContactEmail.text.toString(),
                    memo = binding.etAddContactMemo.text.toString(),
                    profileImage = imageUri.toString(),
                    isLiked = false,
                    alarm = timeString

                )
            )
            val time = calTime()
            okClick?.onClick(binding.etAddContactName.text.toString(), time)
            dismiss()
        }

        binding.ivAddContactDefaultImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        alarmLinearLayoutList.forEachIndexed { idx, linearLayout ->
            linearLayout.setOnClickListener {
                linearLayout.setColor(idx)
            }
        }
    }

    private fun LinearLayout.setColor(position: Int) {
        alarmLinearLayoutList.forEach {
            if (it == this) {
                it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                selectedTime = position
            } else {
                it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
        alarmTextViewList.forEachIndexed { index, textView ->
            if (index == position) {
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
        }
    }

    private fun calTime(): Int {
        timeString = requireContext().getString(alarmTimeList[selectedTime])
        return when (selectedTime) {
            0 -> 0
            1 -> 5
            2 -> 300
            3 -> 600
            4 -> 1800
            else -> 0
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
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, departmentList.map { getString(it) })
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
            phoneNumber.length < 13 -> AddContactErrorMessage.INVALID_PHONE_NUMBER_LENGTH  //전화번호의 길이가 일정 수준인지 체크하고 초과했을 때 실행
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