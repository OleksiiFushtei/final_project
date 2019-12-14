package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.CommandInterface
import com.example.final_project.models.CommandModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import kotlin.Exception

class CommandHelper(
    private val apiInterface: ApiInterface
) : CommandInterface.CommandAddInterface,
    CommandInterface.CommandEditInterface,
    CommandInterface.CommandGetInterface,
    CommandInterface.CommandDeleteInterface {

    override fun addCommand(
        commandModel: CommandModel,
        commandSaveListener: CommandInterface.CommandSaveListener
    ) {
        val call =
            apiInterface.addCommand(
                commandModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<CommandModel> {

                override fun onFailure(
                    call: Call<CommandModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> commandSaveListener.onCommandSaveCancelled()
                        else -> commandSaveListener.onCommandSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<CommandModel>,
                    response: Response<CommandModel>
                ) {
                    when {
                        response.isSuccessful -> commandSaveListener.onCommandSaveResponseSuccess()
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
                            commandSaveListener.onCommandSaveResponseFailure(
                                errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun editCommand(
        commandModel: CommandModel,
        commandSaveListener: CommandInterface.CommandSaveListener
    ) {
        val call =
            apiInterface.editCommand(
                commandModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<CommandModel> {

                override fun onFailure(
                    call: Call<CommandModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> commandSaveListener.onCommandSaveCancelled()
                        else -> commandSaveListener.onCommandSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<CommandModel>,
                    response: Response<CommandModel>
                ) {
                    when {
                        response.isSuccessful -> commandSaveListener.onCommandSaveResponseSuccess()
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
                            commandSaveListener.onCommandSaveResponseFailure(
                                errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun getCommand(
        id: Int,
        commandGetListener: CommandInterface.CommandGetListener
    ) {
        val call =
            apiInterface.getCommand(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<CommandModel> {

                override fun onFailure(
                    call: Call<CommandModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> commandGetListener.onCommandGetCancelled()
                        else -> commandGetListener.onCommandGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<CommandModel>,
                    response: Response<CommandModel>
                ) {
                    when {
                        response.isSuccessful -> commandGetListener.onCommandGetResponseSuccess(
                            commandModel = response.body()!!
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
                            commandGetListener.onCommandGetResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun deleteCommand(
        id: Int,
        commandDeleteListener: CommandInterface.CommandDeleteListener
    ) {
        val call =
            apiInterface.deleteCommand(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<CommandModel> {

                override fun onFailure(
                    call: Call<CommandModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> commandDeleteListener.onCommandDeleteCancelled()
                        else -> commandDeleteListener.onCommandDeleteFailure()
                    }
                }

                override fun onResponse(
                    call: Call<CommandModel>,
                    response: Response<CommandModel>
                ) {
                    when {
                        response.isSuccessful -> commandDeleteListener.onCommandDeleteResponseSuccess()
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
                            commandDeleteListener.onCommandDeleteResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}