package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ControllersListInterface
import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ControllersListHelper(
    private val apiInterface: ApiInterface
) : ControllersListInterface {

    override fun getListOfController(
        controllerListListener: ControllersListInterface.ControllersListListener
    ) {
        val call =
            apiInterface.listControllers()
        call.enqueue(
            object :
                retrofit2.Callback<List<ControllerListItemModel>> {
                override fun onFailure(
                    call: Call<List<ControllerListItemModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> controllerListListener.onGetControllersListCancelled()
                        else -> controllerListListener.onGetControllerListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<ControllerListItemModel>>,
                    response: Response<List<ControllerListItemModel>>
                ) {
                    when {
                        response.isSuccessful -> controllerListListener.onGetControllersListResponseSuccess(
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
                            controllerListListener.onGetControllersListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }

}