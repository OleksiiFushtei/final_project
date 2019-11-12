package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SensorTypeInterface
import com.example.final_project.models.SensorTypeModel
import retrofit2.Call
import retrofit2.Response

class SensorTypeHelper(
    private val apiInterface: ApiInterface
) : SensorTypeInterface {

    override fun getSensorType(
        id: Int,
        sensorTypeListener: SensorTypeInterface.SensorTypeListener
    ) {
        val call =
            apiInterface.getSensorType(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<SensorTypeModel> {
                override fun onFailure(
                    call: Call<SensorTypeModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorTypeListener.onSensorTypeGetCancelled()
                        else -> sensorTypeListener.onSensorTypeGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SensorTypeModel>,
                    response: Response<SensorTypeModel>
                ) {
                    when {
                        response.isSuccessful -> sensorTypeListener.onSensorTypeGetResponseSuccess(response.body()!!)
                        else -> sensorTypeListener.onSensorTypeGetResponseFailure()
                    }
                }

            }
        )
    }
}