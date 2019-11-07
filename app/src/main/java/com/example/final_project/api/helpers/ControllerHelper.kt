package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerInterface
import com.example.final_project.models.ControllerModel
import retrofit2.Call
import retrofit2.Response

class ControllerHelper(
    private val apiInterface: ApiInterface
) : ControllerInterface.ControllerGetInterface,
    ControllerInterface.ControllerAddInterface,
    ControllerInterface.ControllerEditInterface,
    ControllerInterface.ControllerDeleteInterface {

    override fun addController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerInterface.ControllerSaveListener
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
                        else -> controllerSaveListener.onControllerSaveResponseFailure()
                    }
                }
            }
        )
    }

    override fun editController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerInterface.ControllerSaveListener
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
                        else -> controllerSaveListener.onControllerSaveResponseFailure()
                    }
                }
            }
        )
    }

    override fun deleteController(
        id: Int,
        controllerDeleteListener: ControllerInterface.ControllerDeleteListener
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
                        else -> controllerDeleteListener.onDeleteControllerResponseFailure()
                    }
                }
            }
        )
    }

    override fun getController(
        id: Int,
        controllerGetListener: ControllerInterface.ControllerGetListener
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
                        else -> controllerGetListener.onGetControllerResponseFailure()
                    }
                }
            }
        )
    }

}