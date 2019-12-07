package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.CommandsListInterface
import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class CommandsListHelper(
    private val apiInterface: ApiInterface
) : CommandsListInterface {

    override fun getListOfCommands(
        id: Int,
        commandListListener: CommandsListInterface.CommandListListener
    ) {
        val call =
            apiInterface.listCommands(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<CommandModel>> {

                override fun onFailure(
                    call: Call<List<CommandModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> commandListListener.onGetCommandsListCancelled()
                        else -> commandListListener.onGetCommandsListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<CommandModel>>,
                    response: Response<List<CommandModel>>
                ) {
                    when {
                        response.isSuccessful -> commandListListener.onGetCommandsListResponseSuccess(
                            list = ArrayList(
                                response.body()!!
                            )
                        )
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            commandListListener.onGetCommandsListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}