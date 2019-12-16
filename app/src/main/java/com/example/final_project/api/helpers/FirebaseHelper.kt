package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.FirebaseInteface
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class FirebaseHelper(
    private val apiInterface: ApiInterface
) : FirebaseInteface {

    override fun postToken(
        token: String,
        postTokenListener: FirebaseInteface.PostTokenListener
    ) {
        val call =
            apiInterface.postToken(
                token
            )
        call.enqueue(
            object :
                retrofit2.Callback<String> {

                override fun onFailure(
                    call: Call<String>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> postTokenListener.onPostTokenCancelled()
                        else -> postTokenListener.onPostTokenFailure()
                    }
                }

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    when {
                        response.isSuccessful -> postTokenListener.onPostTokenResponseSuccess(
                            token = response.body()!!
                        )
                        else -> {
                            val errorModel =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            postTokenListener.onPostTokenResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun deleteToken(
        deleteTokenListener: FirebaseInteface.DeleteTokenListener
    ) {
        val call =
            apiInterface.deleteToken()
        call.enqueue(
            object :
                retrofit2.Callback<String> {

                override fun onFailure(
                    call: Call<String>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deleteTokenListener.onDeleteTokenCancelled()
                        else -> deleteTokenListener.onDeleteTokenFailure()
                    }
                }

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    when {
                        response.isSuccessful -> deleteTokenListener.onDeleteTokenResponseSuccess()
                        else -> {
                            val errorModel =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            deleteTokenListener.onDeleteTokenResponseFailure(errorModel = errorModel)
                        }
                    }
                }

            }
        )
    }
}