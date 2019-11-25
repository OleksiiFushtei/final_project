package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.DeviceInterface
import com.example.final_project.models.DeviceModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DeviceHelper(
    private val apiInterface: ApiInterface
) : DeviceInterface.DeviceGetInterface,
    DeviceInterface.DeviceAddInterface,
    DeviceInterface.DeviceEditInterface,
    DeviceInterface.DeviceDeleteInterface {

    override fun addDevice(
        deviceModel: DeviceModel,
        deviceSaveListener: DeviceInterface.DeviceSaveListener
    ) {
        val call =
            apiInterface.addDevice(
                deviceModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceModel> {
                override fun onFailure(
                    call: Call<DeviceModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceSaveListener.onDeviceSaveCancelled()
                        else -> deviceSaveListener.onDeviceSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceModel>,
                    response: Response<DeviceModel>
                ) {
                    when {
                        response.isSuccessful -> deviceSaveListener.onDeviceSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel: ErrorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            deviceSaveListener.onDeviceSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            })
    }

    override fun editDevice(
        deviceModel: DeviceModel,
        deviceSaveListener: DeviceInterface.DeviceSaveListener
    ) {
        val call =
            apiInterface.editDevice(
                deviceModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceModel> {
                override fun onFailure(
                    call: Call<DeviceModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceSaveListener.onDeviceSaveCancelled()
                        else -> deviceSaveListener.onDeviceSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceModel>,
                    response: Response<DeviceModel>
                ) {
                    when {
                        response.isSuccessful -> deviceSaveListener.onDeviceSaveResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            deviceSaveListener.onDeviceSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            })
    }

    override fun getDevice(
        id: Int,
        deviceGetListener: DeviceInterface.DeviceGetListener
    ) {
        val call =
            apiInterface.getDevice(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceModel> {

                override fun onFailure(
                    call: Call<DeviceModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceGetListener.onDeviceGetCancelled()
                        else -> deviceGetListener.onDeviceGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceModel>,
                    response: Response<DeviceModel>
                ) {
                    when {
                        response.isSuccessful -> deviceGetListener.onDeviceGetResponseSuccess(
                            device = response.body()!!
                        )
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            deviceGetListener.onDeviceGetResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            })
    }

    override fun deleteDevice(
        id: Int,
        deviceDeleteListener: DeviceInterface.DeviceDeleteListener
    ) {
        val call =
            apiInterface.deleteDevice(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceModel> {

                override fun onFailure(
                    call: Call<DeviceModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceDeleteListener.onDeviceDeleteCancelled()
                        else -> deviceDeleteListener.onDeviceDeleteFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceModel>,
                    response: Response<DeviceModel>
                ) {
                    when {
                        response.isSuccessful -> deviceDeleteListener.onDeviceDeleteResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
                            deviceDeleteListener.onDeviceDeleteResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

}