package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ScriptInterface
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ScriptHelper(
    private val apiInterface: ApiInterface
) : ScriptInterface.ScriptAddInterface,
    ScriptInterface.ScriptEditInterface,
    ScriptInterface.ScriptGetInterface,
    ScriptInterface.ScriptDeleteInterface {

    override fun addScript(
        scriptModel: ScriptModel,
        scriptSaveListener: ScriptInterface.ScriptSaveListener
    ) {
        val call =
            apiInterface.addScript(
                scriptModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<ScriptModel> {

                override fun onFailure(
                    call: Call<ScriptModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> scriptSaveListener.onScriptSaveCancelled()
                        else -> scriptSaveListener.onScriptSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ScriptModel>,
                    response: Response<ScriptModel>
                ) {
                    when {
                        response.isSuccessful -> scriptSaveListener.onScriptSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            scriptSaveListener.onScriptSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun editScript(
        scriptModel: ScriptModel,
        scriptSaveListener: ScriptInterface.ScriptSaveListener
    ) {
        val call =
            apiInterface.editScript(
                scriptModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<ScriptModel> {

                override fun onFailure(
                    call: Call<ScriptModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> scriptSaveListener.onScriptSaveCancelled()
                        else -> scriptSaveListener.onScriptSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ScriptModel>,
                    response: Response<ScriptModel>
                ) {
                    when {
                        response.isSuccessful -> scriptSaveListener.onScriptSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            scriptSaveListener.onScriptSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun getScript(
        id: Int,
        scriptGetListener: ScriptInterface.ScriptGetListener
    ) {
        val call =
            apiInterface.getScript(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<ScriptModel> {

                override fun onFailure(
                    call: Call<ScriptModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> scriptGetListener.onScriptGetCancelled()
                        else -> scriptGetListener.onScriptGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ScriptModel>,
                    response: Response<ScriptModel>
                ) {
                    when {
                        response.isSuccessful -> scriptGetListener.onScriptGetResponseSuccess(
                            scriptModel = response.body()!!
                        )
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            scriptGetListener.onScriptGetResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun deleteScript(
        id: Int,
        scriptDeleteListener: ScriptInterface.ScriptDeleteListener
    ) {
        val call =
            apiInterface.deleteScript(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<ScriptModel> {

                override fun onFailure(
                    call: Call<ScriptModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> scriptDeleteListener.onScriptDeleteCancelled()
                        else -> scriptDeleteListener.onScriptDeleteFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ScriptModel>,
                    response: Response<ScriptModel>
                ) {
                    when {
                        response.isSuccessful -> scriptDeleteListener.onScriptDeleteResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            scriptDeleteListener.onScriptDeleteResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

}