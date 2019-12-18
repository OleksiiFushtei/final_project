package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.DeviceAccessAddUserInterface
import com.example.final_project.models.DeviceAccessModel
import com.example.final_project.models.DeviceListItemModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DeviceAccessAddUserHelper(
    private val apiInterface: ApiInterface
) : DeviceAccessAddUserInterface {

    override fun addUser(
        deviceAccessModel: DeviceAccessModel,
        addUserToListListener: DeviceAccessAddUserInterface.AddUserToListListener
    ) {
        val call =
            apiInterface.addAccessToDevice(
                deviceAccessModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceListItemModel> {

                override fun onFailure(
                    call: Call<DeviceListItemModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> addUserToListListener.onAddUserToListCancelled()
                        else -> addUserToListListener.onAddUserToListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceListItemModel>,
                    response: Response<DeviceListItemModel>
                ) {
                    when {
                        response.isSuccessful -> addUserToListListener.onAddUserToListResponseSuccess()
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
                            addUserToListListener.onAddUserToListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}