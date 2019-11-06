package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerAddInterface
import com.example.final_project.models.ControllerModel
import retrofit2.Call
import retrofit2.Response

class ControllerAddHepler(
    private val apiInterface: ApiInterface
) : ControllerAddInterface {
    override fun addController(

        controllerModel: ControllerModel,
        controllerAddListener: ControllerAddInterface.ControllerAddListener
    ) {
        val call = apiInterface.addController(controllerModel)
        call.enqueue(object: retrofit2.Callback<ControllerModel> {
            override fun onFailure(
                call: Call<ControllerModel>,
                t: Throwable
            ) {
                when {
                    call.isCanceled -> controllerAddListener.onControllerAddCancelled()
                    else -> controllerAddListener.onControllerAddFailure()
                }
            }

            override fun onResponse(
                call: Call<ControllerModel>,
                response: Response<ControllerModel>
            ) {
                when {
                    response.isSuccessful -> controllerAddListener.onControllerAddResponseSuccess()
                    else -> controllerAddListener.onControllerAddResponseFailure()
                }
            }

        })
    }
}