package com.example.workswhale.addContactDialog

import androidx.annotation.StringRes
import com.example.workswhale.R

enum class AddContactErrorMessage(
    @StringRes val message: Int,
) {
    EMPTY_NAME(R.string.empty_name_error),
    EMPTY_PHONE_NUMBER(R.string.empty_phone_number_error),
    EMPTY_EMAIL(R.string.empty_email_error),

    INVALID_PHONE_NUMBER(R.string.invalid_phone_number_error),
    INVALID_PHONE_NUMBER_LENGTH(R.string.invalid_phone_number_length_error),
    INVALID_EMAIL(R.string.invalid_email_error),
}