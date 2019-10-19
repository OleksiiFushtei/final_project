package com.example.final_project.api.interfaces

import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel

interface LoginInterface {
    fun login(
        signInDataModel: SignInDataModel,
        loginListener: LoginListener
    )

    interface LoginListener {
        fun onLoginResponseSuccess(
            token: TokenModel
        )

        fun onLoginResponseFailure()
        fun onLoginCancelled()
        fun onLoginFailure()
    }
}