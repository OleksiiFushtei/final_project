package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SensorTypeInterface
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SensorTypeModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class SensorTypeHelper(
    private val apiInterface: ApiInterface
) : SensorTypeInterface {

    override fun getSensorTypes(
        sensorTypeListener: SensorTypeInterface.SensorTypesListener
    ) {
        val call =
            apiInterface.sensorTypes
        call.enqueue(
            object :
                retrofit2.Callback<List<SensorTypeModel>> {
                override fun onFailure(
                    call: Call<List<SensorTypeModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorTypeListener.onSensorTypesGetCancelled()
                        else -> sensorTypeListener.onSensorTypesGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<SensorTypeModel>>,
                    response: Response<List<SensorTypeModel>>
                ) {
                    when {
                        response.isSuccessful -> sensorTypeListener.onSensorTypesGetResponseSuccess(
                            ArrayList(
                                response.body()!!
                            )
                        )
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            sensorTypeListener.onSensorTypesGetResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}