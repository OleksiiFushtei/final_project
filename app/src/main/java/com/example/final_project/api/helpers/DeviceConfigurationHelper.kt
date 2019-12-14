package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.DeviceConfigurationInterface
import com.example.final_project.models.DeviceConfigurationModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DeviceConfigurationHelper(
    private val apiInterface: ApiInterface
) : DeviceConfigurationInterface.DeviceConfigurationAddInterface,
    DeviceConfigurationInterface.DeviceConfigurationEditInterface,
    DeviceConfigurationInterface.DeviceConfigurationGetInterface,
    DeviceConfigurationInterface.DeviceConfigurationDeleteInterface {

    override fun addDeviceConfiguration(
        deviceConfigurationModel: DeviceConfigurationModel,
        deviceConfigurationSaveListener: DeviceConfigurationInterface.DeviceConfigurationSaveListener
    ) {
        val call =
            apiInterface.addConfig(
                deviceConfigurationModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceConfigurationModel> {

                override fun onFailure(
                    call: Call<DeviceConfigurationModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceConfigurationSaveListener.onDeviceConfigurationSaveCancelled()
                        else -> deviceConfigurationSaveListener.onDeviceConfigurationSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceConfigurationModel>,
                    response: Response<DeviceConfigurationModel>
                ) {
                    when {
                        response.isSuccessful -> deviceConfigurationSaveListener.onDeviceConfigurationSaveResponseSuccess(
                            config = response.body()!!
                        )
                        else -> {
                            val errorModel: ErrorModel? =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            deviceConfigurationSaveListener.onDeviceConfigurationSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun editDeviceConfiguration(
        deviceConfigurationModel: DeviceConfigurationModel,
        deviceConfigurationSaveListener: DeviceConfigurationInterface.DeviceConfigurationSaveListener
    ) {
        val call =
            apiInterface.editConfig(
                deviceConfigurationModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceConfigurationModel> {

                override fun onFailure(
                    call: Call<DeviceConfigurationModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceConfigurationSaveListener.onDeviceConfigurationSaveCancelled()
                        else -> deviceConfigurationSaveListener.onDeviceConfigurationSaveFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceConfigurationModel>,
                    response: Response<DeviceConfigurationModel>
                ) {
                    when {
                        response.isSuccessful -> deviceConfigurationSaveListener.onDeviceConfigurationSaveResponseSuccess(
                            config = response.body()!!
                        )
                        else -> {
                            val errorModel: ErrorModel? =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            deviceConfigurationSaveListener.onDeviceConfigurationSaveResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun getDeviceConfiguration(
        id: Int,
        deviceConfigurationGetListener: DeviceConfigurationInterface.DeviceConfigurationGetListener
    ) {
        val call =
            apiInterface.getConfig(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceConfigurationModel> {

                override fun onFailure(
                    call: Call<DeviceConfigurationModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceConfigurationGetListener.onDeviceConfigurationGetCancelled()
                        else -> deviceConfigurationGetListener.onDeviceConfigurationGetFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceConfigurationModel>,
                    response: Response<DeviceConfigurationModel>
                ) {
                    when {
                        response.isSuccessful -> deviceConfigurationGetListener.onDeviceConfigurationGetResponseSuccess(
                            response.body()!!
                        )
                        else -> {
                            val errorModel: ErrorModel? =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            deviceConfigurationGetListener.onDeviceConfigurationGetResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun deleteDeviceConfiguration(
        id: Int,
        deviceConfigurationDeleteListener: DeviceConfigurationInterface.DeviceConfigurationDeleteListener
    ) {
        val call =
            apiInterface.deleteConfig(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceConfigurationModel> {

                override fun onFailure(
                    call: Call<DeviceConfigurationModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deviceConfigurationDeleteListener.onDeviceConfigurationDeleteCancelled()
                        else -> deviceConfigurationDeleteListener.onDeviceConfigurationDeleteFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceConfigurationModel>,
                    response: Response<DeviceConfigurationModel>
                ) {
                    when {
                        response.isSuccessful -> deviceConfigurationDeleteListener.onDeviceConfigurationDeleteResponseSuccess()
                        else -> {
                            val errorModel: ErrorModel? =
                                try {
                                    Gson().fromJson(
                                        response.errorBody().toString(),
                                        ErrorModel::class.java
                                    )
                                } catch (e: Exception) {
                                    null
                                }
                            deviceConfigurationDeleteListener.onDeviceConfigurationDeleteResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}