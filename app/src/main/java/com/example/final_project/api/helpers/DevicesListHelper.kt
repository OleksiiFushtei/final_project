package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.DevicesListInterface
import com.example.final_project.models.DeviceModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DevicesListHelper(
    private val apiInterface: ApiInterface
) : DevicesListInterface {

    override fun getListOfDevices(
        id: Int,
        devicesListListener: DevicesListInterface.DevicesListListener
    ) {
        val call =
            apiInterface.listDevices(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<DeviceModel>> {

                override fun onFailure(
                    call: Call<List<DeviceModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> devicesListListener.onGetDevicesListCancelled()
                        else -> devicesListListener.onGetDevicesListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<DeviceModel>>,
                    response: Response<List<DeviceModel>>
                ) {
                    when {
                        response.isSuccessful ->
                            devicesListListener.onGetDevicesListResponseSuccess(
                                list = ArrayList(
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
                            devicesListListener.onGetDevicesListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }
}