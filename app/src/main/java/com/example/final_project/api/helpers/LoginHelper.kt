package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.LoginInterface
import com.example.final_project.models.SignInDataModel
import com.example.final_project.models.TokenModel
import retrofit2.Call
import retrofit2.Response

class LoginHelper(
    private val apiInterface: ApiInterface
) : LoginInterface {

    override fun login(
        signInDataModel: SignInDataModel,
        loginListener: LoginInterface.LoginListener
    ) {
        val call =
            apiInterface.login(
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
                        call.isCanceled -> loginListener.onLoginCancelled()
                        else -> loginListener.onLoginFailure()
                    }
                }

                override fun onResponse(
                    call: Call<TokenModel>,
                    response: Response<TokenModel>
                ) {
                    when {
                        response.isSuccessful -> loginListener.onLoginResponseSuccess(
                            response.body()!!
                        )
                        else -> loginListener.onLoginResponseFailure()
                    }
                }

            })
    }

}