package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerInterface
import com.example.final_project.models.ControllerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContollerHelper(
    private val apiInterface: ApiInterface
) : ControllerInterface {

    override fun getControllers(
        controllerModel: ControllerModel,
        controllerListener: ControllerInterface.ControllerListener
    ) {
        val controllersList: ArrayList<ControllerModel> = ArrayList()
        val call = apiInterface.getListOfControllers(controllersList)
        call.enqueue(
            object :
                retrofit2.Callback<ArrayList<ControllerModel>> {
                override fun onFailure(
                    call: Call<List<ControllerModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerListener.onGetControllersCancelled()
                        else -> controllerListener.onGetControllerFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<ControllerModel>>,
                    response: Response<List<ControllerModel>>
                ) {
                    when {
                        response.isSuccessful -> controllerListener.onGetControllersResponseSuccess()
                        else -> controllerListener.onGetControllersResponseFailure()
                    }
                }

            })
    }
}