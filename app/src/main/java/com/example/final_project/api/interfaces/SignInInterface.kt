package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel

interface SignInInterface {
    fun signIn(
        signInDataModel: SignInDataModel,
        signInListener: SignInListener
    )

    interface SignInListener {
        fun onSignInResponseSuccess(
            token: TokenModel
        )

        fun onSignInResponseFailure(
            errorModel: ErrorModel
        )

        fun onSignInCancelled()
        fun onSignInFailure()
    }
}