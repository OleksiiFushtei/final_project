package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllerAccessAddUserInterface
import com.example.final_project.models.ControllerAccessModel
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ControllerAccessAddUserHelper(
    private val apiInterface: ApiInterface
) : ControllerAccessAddUserInterface {

    override fun addUser(
        controllerAccessModel: ControllerAccessModel,
        addUserToListListener: ControllerAccessAddUserInterface.AddUserToListListener
    ) {
        val call =
            apiInterface.addAccessToController(
                controllerAccessModel
            )
        call.enqueue(
            object :
                retrofit2.Callback<ControllerListItemModel> {

                override fun onFailure(
                    call: Call<ControllerListItemModel>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> addUserToListListener.onAddUserToListCancelled()
                        else -> addUserToListListener.onAddUserToListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<ControllerListItemModel>,
                    response: Response<ControllerListItemModel>
                ) {
                    when {
                        response.isSuccessful -> addUserToListListener.onAddUserToListResponseSuccess()
                        else -> {
                            val gson =
                                Gson()
                            val errorModel =
                                gson.fromJson(
                                    response.errorBody().toString(),
                                    ErrorModel::class.java
                                )
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