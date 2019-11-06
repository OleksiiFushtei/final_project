package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerGetInterface
import com.example.final_project.models.ControllerModel
import retrofit2.Call
import retrofit2.Response

class ControllerGetHelper(
    private val apiInterface: ApiInterface
): ControllerGetInterface {
    override fun getController(
        id: Int,
        controllerGetListener: ControllerGetInterface.ControllerGetListener
    ) {
        val call = apiInterface.getController(id)
        call.enqueue(object: retrofit2.Callback<ControllerModel> {
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
                    response.isSuccessful -> controllerGetListener.onGetControllerResponseSuccess(response.body()!!)
                    else -> controllerGetListener.onGetControllerResponseFailure()
                }
            }

        })
    }


}