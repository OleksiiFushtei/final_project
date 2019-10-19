package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SignUpInterface
import com.example.final_project.models.SignUpDataModel
import retrofit2.Call
import retrofit2.Response

class SignUpHelper(
    private val apiInterface: ApiInterface
) : SignUpInterface {

    override fun signUp(
        signUpDataModel: SignUpDataModel,
        signUpListener: SignUpInterface.SignUpListener
    ) {
        val call =
            apiInterface.registerUser(
                signUpDataModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<SignUpDataModel> {
                override fun onFailure(
                    call: Call<SignUpDataModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> signUpListener.onSignUpCancelled()
                        else -> signUpListener.onSignUpFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SignUpDataModel>,
                    response: Response<SignUpDataModel>
                ) {
                    when {
                        response.isSuccessful -> signUpListener.onSignUpResponseSuccess()
                        else -> signUpListener.onSignUpResponseFailure()
                    }
                }

            })
    }
}