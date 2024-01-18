package com.example.workswhale.editMyProfileDialog

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.workswhale.ConstValues
import com.example.workswhale.databinding.DialogEditMyProfileBinding
import java.util.regex.Pattern

class EditMyProfileDialog(private val userInfo: List<String>, private val userProfileImage: Drawable) : DialogFragment() {

    interface OkClick {
        fun onClick(profileImage: Drawable, name: String, phoneNumber: String, email: String)
    }

    var okClick: OkClick? = null

    private var _binding: DialogEditMyProfileBinding? = null
    private val binding get() = _binding!!

    private var selectedProfile: Uri? = null
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.ivEditProfileProfile.setImageURI(uri)
            binding.ivEditProfileProfile.scaleType = ImageView.ScaleType.CENTER_CROP
            selectedProfile = uri
        }
    }

    private val editTexts
        get() = listOf(
            binding.etEditProfileEmail,
            binding.etEditProfileName,
            binding.etEditProfilePhoneNumber
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEditMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnEditProfileCheck.isEnabled = false  // 버튼을 비활성화 시킴

            ivEditProfileProfile.setImageDrawable(userProfileImage)
            ivEditProfileProfile.scaleType = ImageView.ScaleType.CENTER_CROP
            etEditProfileName.setText(userInfo[0])
            etEditProfilePhoneNumber.setText(userInfo[1])
            etEditProfileEmail.setText(userInfo[2])

            setAddButtonEnable()
            setTextChangeLisener()
            setFocusChangedLisener()

            btnEditProfileCancel.setOnClickListener {
                dismiss()
            }

            ivEditProfileProfile.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            btnEditProfileCheck.setOnClickListener {
                // 마이페이지프래그먼트로 데이터 넘기기
                okClick?.onClick(
                    ivEditProfileProfile.drawable,
                    etEditProfileName.text.toString(),
                    etEditProfilePhoneNumber.text.toString(),
                    etEditProfileEmail.text.toString())
                dismiss()
            }
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
        binding.etEditProfilePhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
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
            binding.etEditProfileName -> error = getMessageValidName()
            binding.etEditProfilePhoneNumber -> error = getMessageValidPhoneNumber()
            binding.etEditProfileEmail -> error = getMessageValidEmail()
        }
    }

    // 이름부분에 에러메세지 출력하는 함수
    private fun getMessageValidName() : String? {
        val name = binding.etEditProfileName.text.toString()
        return when{
            name.isBlank() -> EditMyProfileErrorMessage.EMPTY_NAME //이름 부분이 공백이면 AddContactErrorMessage에 EMPTY_NAME을 불러와 메세지를 띄운다.
            else -> null
        }?.message?.let{ getString(it) }
    }

    // 전화번호에 에러메세지를 출력하는 함수
    // 전화번호의 길이와 전화번호 시작이 010인지 체크하는 함수의 자리가 바뀌면 오류가 생김
    // -> 010인지 체크하는 함수는 문자열을 잘라서 하기 때문에 처음 문자를 입력받을 때는 그 길이를 충족하지 않아 오류가 생김
    private fun getMessageValidPhoneNumber() : String?{
        val number = binding.etEditProfilePhoneNumber.text.toString()
        return when{
            number.isBlank() -> EditMyProfileErrorMessage.EMPTY_PHONE_NUMBER  //전화번호칸이 공백일 때 실행
            number.substring(0 until 3) != "010" -> EditMyProfileErrorMessage.INVALID_PHONE_NUMBER  //전화번호가 010으로 시작하지 않을 때 실행
            number.length < 13 -> EditMyProfileErrorMessage.INVALID_PHONE_NUMBER_LENGTH  //전화번호의 길이가 일정 수준인지 체크하고 초과했을 때 실행
            else -> null
        }?.message?.let{getString(it)}
    }

    //이메일에 에러메시지를 출력하는 함수
    private fun getMessageValidEmail() : String?{
        val email = binding.etEditProfileEmail.text.toString()
        return when{
            email.isBlank() -> EditMyProfileErrorMessage.EMPTY_EMAIL //이메일칸이 공백일 때 실행
            !email.emailValidCheck() -> EditMyProfileErrorMessage.INVALID_EMAIL //이메일 형식이 맞이 않을 때 실행
            else -> null
        }?.message?.let{getString(it)}
    }

    //이메일의 형식이 알맞는지 체크하는 함수
    private fun String.emailValidCheck() : Boolean{
        return Pattern.matches(ConstValues.EMAIL_VALID_CHECK, this)
    }

    //버튼을 활성화하는 함수
    private fun setAddButtonEnable() {
        binding.btnEditProfileCheck.isEnabled = getMessageValidName() == null
                && getMessageValidEmail() == null
                && getMessageValidPhoneNumber() == null
    }
}