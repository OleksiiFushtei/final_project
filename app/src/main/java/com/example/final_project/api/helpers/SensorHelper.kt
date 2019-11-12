package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.SensorInterface
import com.example.final_project.models.SensorModel
import retrofit2.Call
import retrofit2.Response

class SensorHelper(
    private val apiInterface: ApiInterface
) : SensorInterface.SensorGetInterface,
    SensorInterface.SensorAddInterface,
    SensorInterface.SensorEditInterface,
    SensorInterface.SensorDeleteInterface {

    override fun addSensor(
        sensorModel: SensorModel,
        sensorSaveListener: SensorInterface.SensorSaveListener
    ) {
        val call =
            apiInterface.addSensor(
                sensorModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<SensorModel> {

                override fun onFailure(
                    call: Call<SensorModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorSaveListener.onSensorSaveCancelled()
                        else -> sensorSaveListener.onSensorSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SensorModel>,
                    response: Response<SensorModel>
                ) {
                    when {
                        response.isSuccessful -> sensorSaveListener.onSensorSaveResponseSuccess()
                        else -> sensorSaveListener.onSensorSaveResponseFailure()
                    }
                }

            }
        )
    }

    override fun editSensor(
        sensorModel: SensorModel,
        sensorSaveListener: SensorInterface.SensorSaveListener
    ) {
        val call =
            apiInterface.editSensor(
                sensorModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<SensorModel> {

                override fun onFailure(
                    call: Call<SensorModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorSaveListener.onSensorSaveCancelled()
                        else -> sensorSaveListener.onSensorSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SensorModel>,
                    response: Response<SensorModel>
                ) {

                    when {
                        response.isSuccessful -> sensorSaveListener.onSensorSaveResponseSuccess()
                        else -> sensorSaveListener.onSensorSaveResponseFailure()
                    }
                }

            }
        )
    }

    override fun getSensor(
        id: Int,
        sensorGetListener: SensorInterface.SensorGetListener
    ) {
        val call =
            apiInterface.getSensor(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<SensorModel> {

                override fun onFailure(
                    call: Call<SensorModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorGetListener.onSensorGetCancelled()
                        else -> sensorGetListener.onSensorGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SensorModel>,
                    response: Response<SensorModel>
                ) {
                    when {
                        response.isSuccessful -> sensorGetListener.onSensorGetResponseSuccess(
                            response.body()!!
                        )
                        else -> sensorGetListener.onSensorGetResponseFailure()
                    }
                }

            }
        )
    }

    override fun deleteSensor(
        id: Int,
        sensorDeleteListener: SensorInterface.SensorDeleteListener
    ) {
        val call =
            apiInterface.deleteSensor(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<SensorModel> {

                override fun onFailure(
                    call: Call<SensorModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> sensorDeleteListener.onSensorDeleteCancelled()
                        else -> sensorDeleteListener.onSensorDeleteFailure()
                    }
                }

                override fun onResponse(
                    call: Call<SensorModel>,
                    response: Response<SensorModel>
                ) {
                    when {
                        response.isSuccessful -> sensorDeleteListener.onSensorDeleteResponseSuccess()
                        else -> sensorDeleteListener.onSensorDeleteResponseFailure()
                    }
                }

            }
        )
    }
}