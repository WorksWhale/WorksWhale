package com.example.workswhale

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.workswhale.databinding.DialogEditMyProfileBinding
import java.util.regex.Pattern


//1. 이름 유효성검사 ( 공란인지의 여부만 판단하기 )
//2. 전화번호 유효성검사 ( 010은 기본으로 설정하고 뒤에 오는수가 8자리가 맞는지 확인 )
//3. 이메일 유효성검사

//다이얼로그 영역
//이름, 전화번호, 이메일을 입력 받기
//입력 받은 정보의 유효성 검사 진행하기
//입력 받은 정보를 마이 페이지에 바로 업데이트하기

class EditMyProfileDialog : DialogFragment() {

    interface OkClick {
        fun onClick(name: String, phoneNumber: String, email: String)
    }

    var okClick: OkClick? = null

    private var _binding: DialogEditMyProfileBinding? = null
    private val binding get() = _binding!!
    private val editTexts get() = listOf(binding.editTvEmail, binding.editTvName, binding.editTvPhoneNumber)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheck.isEnabled = false  // 버튼을 비활성화 시킴
        setTextChangeLisener()
        setFocusChangedLisener()

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnCheck.setOnClickListener {
            // 마이페이지프래그먼트로 데이터 넘기기
            okClick?.onClick(
                binding.editTvName.text.toString(),
                binding.editTvPhoneNumber.text.toString(),
                binding.editTvEmail.text.toString())
            dismiss()
        }
    }

    // 전체적으로 에러가 있는지 여부를 판단해 에러가 있는 경우(또는 아무것도 없는 경우) 버튼을 비활성화 시키는 함수
    private fun setTextChangeLisener(){
        editTexts.forEach{editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
                setAddButtonEnable()
            }
        }
        binding.editTvPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun setFocusChangedLisener() {
        editTexts.forEach { editText ->
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus.not()) {
                    editText.setErrorMessage()
                    setAddButtonEnable()
                }
            }
        }
    }

    // 만약에 입력받은 코드에 error가 날 경우 각각 에러메세지가 있는 함수를 실행
    private fun EditText.setErrorMessage(){
        when(this) {
            binding.editTvName -> error = getMessageValidName()
            binding.editTvPhoneNumber -> error = getMessageValidPhoneNumber()
            binding.editTvEmail -> error = getMessageValidEmail()
        }
    }

    // 이름부분에 에러메세지 출력하는 함수
    private fun getMessageValidName() : String? {
        val name = binding.editTvName.text.toString()
        return when{
            name.isBlank() -> AddContactErrorMessage.EMPTY_NAME //이름 부분이 공백이면 AddContactErrorMessage에 EMPTY_NAME을 불러와 메세지를 띄운다.
            else -> null
        }?.message?.let{ getString(it) }
    }

    // 전화번호에 에러메세지를 출력하는 함수
    // 전화번호의 길이와 전화번호 시작이 010인지 체크하는 함수의 자리가 바뀌면 오류가 생김
    // -> 010인지 체크하는 함수는 문자열을 잘라서 하기 때문에 처음 문자를 입력받을 때는 그 길이를 충족하지 않아 오류가 생김
    private fun getMessageValidPhoneNumber() : String?{
        val number = binding.editTvPhoneNumber.text.toString()
        return when{
            number.isBlank() -> AddContactErrorMessage.EMPTY_PHONE_NUMBER  //전화번호칸이 공백일 때 실행
            number.length < 13 -> AddContactErrorMessage.INVALID_PHONE_NUMBER_LENGTH  //전화번호의 길이가 일정 수준인지 체크하고 초과했을 때 실행
            number.substring(0 until 3) != "010" -> AddContactErrorMessage.INVALID_PHONE_NUMBER  //전화번호가 010으로 시작하지 않을 때 실행
            else -> null
        }?.message?.let{getString(it)}
    }

    //이메일에 에러메시지를 출력하는 함수
    private fun getMessageValidEmail() : String?{
        val email = binding.editTvEmail.text.toString()
        return when{
            email.isBlank() -> AddContactErrorMessage.EMPTY_EMAIL //이메일칸이 공백일 때 실행
            email.emailValidCheck() == false -> AddContactErrorMessage.INVALID_EMAIL //이메일 형식이 맞이 않을 때 실행
            else -> null
        }?.message?.let{getString(it)}
    }

    //이메일의 형식이 알맞는지 체크하는 함수
    private fun String.emailValidCheck() : Boolean{
        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return Pattern.matches(emailValidation, this)
    }

    //버튼을 활성화하는 함수
    private fun setAddButtonEnable() {
        binding.btnCheck.isEnabled = getMessageValidName() == null
                && getMessageValidEmail() == null
                && getMessageValidPhoneNumber() == null
    }
}