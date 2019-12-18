package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.DeviceAccessInterface
import com.example.final_project.models.DeviceAccessModel
import com.example.final_project.models.DeviceListItemModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DeviceAccessHelper(
    private val apiInterface: ApiInterface
) : DeviceAccessInterface.DeviceAccessGetListInterface,
    DeviceAccessInterface.DeviceAccessDeleteUserInterface {

    override fun getListOfUsers(
        id: Int,
        getUsersForDevicesListener: DeviceAccessInterface.GetUsersForDevicesListener
    ) {
        val call =
            apiInterface.listUsersForDevices(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<DeviceListItemModel>> {

                override fun onFailure(
                    call: Call<List<DeviceListItemModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> getUsersForDevicesListener.onGetUsersForDevicesCancelled()
                        else -> getUsersForDevicesListener.onGetUsersForDevicesFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<DeviceListItemModel>>,
                    response: Response<List<DeviceListItemModel>>
                ) {
                    when {
                        response.isSuccessful -> getUsersForDevicesListener.onGetUsersFroDevicesResponseSuccess(
                            list = ArrayList(
                                response.body()!!
                            )
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
                            getUsersForDevicesListener.onGetUsersForDevicesResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

    override fun deleteUser(
        id: Int,
        deleteUserFromListListener: DeviceAccessInterface.DeleteUserFromListListener
    ) {
        val call =
            apiInterface.revokeAccessFromDevice(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<DeviceAccessModel> {

                override fun onFailure(
                    call: Call<DeviceAccessModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deleteUserFromListListener.onDeleteUserFromListCancelled()
                        else -> deleteUserFromListListener.onDeleteUserFromListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<DeviceAccessModel>,
                    response: Response<DeviceAccessModel>
                ) {
                    when {
                        response.isSuccessful -> deleteUserFromListListener.onDeleteUserFromListResponseSuccess()
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
                            deleteUserFromListListener.onDeleteUserFromListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }


}