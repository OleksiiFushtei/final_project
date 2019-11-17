package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SignUpDataModel

interface SignUpInterface {
    fun signUp(
        signUpDataModel: SignUpDataModel,
        signUpListener: SignUpListener
    )

    interface SignUpListener {
        fun onSignUpResponseSuccess()
        fun onSignUpResponseFailure(
            errorModel: ErrorModel
        )

        fun onSignUpCancelled()
        fun onSignUpFailure()
    }
}