package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerSaveInterface
import com.example.final_project.models.ControllerModel
import retrofit2.Call
import retrofit2.Response

class ControllerSaveHepler(
    private val apiInterface: ApiInterface
) : ControllerSaveInterface {
    override fun saveController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerSaveInterface.ControllerSaveListener
    ) {
        val call =
            apiInterface.saveController(
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
                        call.isCanceled -> controllerSaveListener.onSaveControllerCancelled()
                        else -> controllerSaveListener.onSaveControllerFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerModel>,
                    response: Response<ControllerModel>
                ) {
                    when {
                        response.isSuccessful -> controllerSaveListener.onSaveControllerResponseSuccess()
                        else -> controllerSaveListener.onSaveControllerResponseFailure()
                    }
                }

            }
        )
    }
}