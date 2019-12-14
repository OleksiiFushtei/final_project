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
        commandsListListener: CommandsListInterface.CommandsListListener
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
                        call.isCanceled -> commandsListListener.onGetCommandsListCancelled()
                        else -> commandsListListener.onGetCommandsListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<CommandModel>>,
                    response: Response<List<CommandModel>>
                ) {
                    when {
                        response.isSuccessful ->
                            commandsListListener.onGetCommandsListResponseSuccess(
                                list = ArrayList(
                                    response.body()!!
                                )
                            )
                        else -> {
                            val errorModel: ErrorModel? =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            commandsListListener.onGetCommandsListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}