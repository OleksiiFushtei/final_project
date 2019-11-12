package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SensorsListInterface
import com.example.final_project.models.SensorModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SensorsListHelper(
    private val apiInterface: ApiInterface
) : SensorsListInterface {
    override fun getListOfSensors(
        id: Int,
        sensorsListListener: SensorsListInterface.SensorsListListener
    ) {
        val call =
            apiInterface.listSensors(
                id
            )
        call.enqueue(
            object :
                Callback<List<SensorModel>> {
                override fun onFailure(
                    call: Call<List<SensorModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorsListListener.onGetSensorsListCancelled()
                        else -> sensorsListListener.onGetSensorsListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<SensorModel>>,
                    response: Response<List<SensorModel>>
                ) {
                    when {
                        response.isSuccessful ->
                            sensorsListListener.onGetSensorsListResponseSuccess(
                                list = ArrayList(
                                    response.body()!!
                                )
                            )
                        else -> sensorsListListener.onGetSensorsListResponseFailure()
                    }
                }

            }
        )
    }

}