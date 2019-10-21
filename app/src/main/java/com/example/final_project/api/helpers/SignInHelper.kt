package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SignInInterface
import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel
import retrofit2.Call
import retrofit2.Response

class SignInHelper(
    private val apiInterface: ApiInterface
) : SignInInterface {

    override fun signIn(
        signInDataModel: SignInDataModel,
        signInListener: SignInInterface.SignInListener
    ) {
        val call =
            apiInterface.signIn(
                signInDataModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<TokenModel> {
                override fun onFailure(
                    call: Call<TokenModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> signInListener.onSignInCancelled()
                        else -> signInListener.onSignInFailure()
                    }
                }

                override fun onResponse(
                    call: Call<TokenModel>,
                    response: Response<TokenModel>
                ) {
                    when {
                        response.isSuccessful -> signInListener.onSignInResponseSuccess(
                            response.body()!!
                        )
                        else -> signInListener.onSignInResponseFailure()
                    }
                }

            })
    }

}




