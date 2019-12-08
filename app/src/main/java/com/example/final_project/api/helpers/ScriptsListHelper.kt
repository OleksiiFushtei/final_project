package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.ScriptsListInterface
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.ScriptModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class ScriptsListHelper(
    private val apiInterface: ApiInterface
) : ScriptsListInterface {

    override fun getListOfScripts(
        id: Int,
        scriptsListListener: ScriptsListInterface.ScriptsListListener
    ) {
        val call =
            apiInterface.listScripts(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<ScriptModel>> {

                override fun onFailure(
                    call: Call<List<ScriptModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> scriptsListListener.onGetScriptsListCancelled()
                        else -> scriptsListListener.onGetScriptsListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<ScriptModel>>,
                    response: Response<List<ScriptModel>>
                ) {
                    when {
                        response.isSuccessful ->
                            scriptsListListener.onGetScriptsListResponseSuccess(
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
                            scriptsListListener.onGetScriptsListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }
            }
        )
    }
}