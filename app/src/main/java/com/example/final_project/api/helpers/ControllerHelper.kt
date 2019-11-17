package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerInterface
import com.example.final_project.api.interfaces.ControllerInterface.*
import com.example.final_project.models.ControllerModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ControllerHelper(
    private val apiInterface: ApiInterface
) : ControllerGetInterface,
    ControllerAddInterface,
    ControllerEditInterface,
    ControllerDeleteInterface {

    override fun addController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerSaveListener
    ) {
        val call =
            apiInterface.addController(
                controllerModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerModel> {

                override fun onFailure(
                    call: Call<ControllerModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerSaveListener.onControllerSaveCancelled()
                        else -> controllerSaveListener.onControllerSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerModel>,
                    response: Response<ControllerModel>
                ) {
                    when {
                        response.isSuccessful -> controllerSaveListener.onControllerSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            controllerSaveListener.onControllerSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }

    override fun editController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerSaveListener
    ) {
        val call =
            apiInterface.editController(
                controllerModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerModel> {

                override fun onFailure(
                    call: Call<ControllerModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerSaveListener.onControllerSaveCancelled()
                        else -> controllerSaveListener.onControllerSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerModel>,
                    response: Response<ControllerModel>
                ) {
                    when {
                        response.isSuccessful -> controllerSaveListener.onControllerSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            controllerSaveListener.onControllerSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }

    override fun getController(
        id: Int,
        controllerGetListener: ControllerGetListener
    ) {
        val call =
            apiInterface.getController(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerModel> {

                override fun onFailure(
                    call: Call<ControllerModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerGetListener.onGetControllerCancelled()
                        else -> controllerGetListener.onGetControllerFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerModel>,
                    response: Response<ControllerModel>
                ) {
                    when {
                        response.isSuccessful -> controllerGetListener.onGetControllerResponseSuccess(
                            response.body()!!
                        )
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            controllerGetListener.onGetControllerResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }

    override fun deleteController(
        id: Int,
        controllerDeleteListener: ControllerDeleteListener
    ) {
        val call =
            apiInterface.deleteController(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerModel> {

                override fun onFailure(
                    call: Call<ControllerModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerDeleteListener.onDeleteControllerCancelled()
                        else -> controllerDeleteListener.onDeleteControllerFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerModel>,
                    response: Response<ControllerModel>
                ) {
                    when {
                        response.isSuccessful -> controllerDeleteListener.onDeleteControllerResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            controllerDeleteListener.onDeleteControllerResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }

}