package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerAccessInterface
import com.example.final_project.models.ControllerAccessModel
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import kotlin.Exception

class ControllerAccessHelper(
    private val apiInterface: ApiInterface
) : ControllerAccessInterface.ControllerAccessGetListInterface,
    ControllerAccessInterface.ControllerAccessDeleteUser {

    override fun getListOfUsers(
        id: Int,
        getUsersForControllersListener: ControllerAccessInterface.GetUsersForControllersListener
    ) {
        val call =
            apiInterface.listUsersForControllers(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<ControllerListItemModel>> {

                override fun onFailure(
                    call: Call<List<ControllerListItemModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> getUsersForControllersListener.onGetUsersForControllerCancelled()
                        else -> getUsersForControllersListener.onGetUsersForControllerFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<ControllerListItemModel>>,
                    response: Response<List<ControllerListItemModel>>
                ) {
                    when {
                        response.isSuccessful -> getUsersForControllersListener.onGetUsersForControllersResponseSuccess(
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
                            getUsersForControllersListener.onGetUsersForControllersResponseFailure(
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
        deleteUserFromListListener: ControllerAccessInterface.DeleteUserFromListListener
    ) {
        val call =
            apiInterface.revokeAccessFromController(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerAccessModel> {

                override fun onFailure(
                    call: Call<ControllerAccessModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> deleteUserFromListListener.onDeleteUserFromListCancelled()
                        else -> deleteUserFromListListener.onDeleteUserFromListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerAccessModel>,
                    response: Response<ControllerAccessModel>
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