package com.example.final_project.api.helpers

import com.example.final_project.api.interfaces.ApiInterface
import com.example.final_project.api.interfaces.MeasuresListInterface
import com.example.final_project.models.ErrorModel
import com.example.final_project.models.MeasureModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class MeasuresListHelper(
    private val apiInterface: ApiInterface
) : MeasuresListInterface {

    override fun getListOfMeasures(
        id: Int,
        measuresListListener: MeasuresListInterface.MeasuresListListener
    ) {
        val call =
            apiInterface.listMeasures(
                id
            )
        call.enqueue(
            object :
                retrofit2.Callback<List<MeasureModel>> {

                override fun onFailure(
                    call: Call<List<MeasureModel>>,
                    t: Throwable
                ) {
                    when {
                        call.isCanceled -> measuresListListener.onGetMeasuresListCancelled()
                        else -> measuresListListener.onGetMeasuresListFailure()
                    }
                }

                override fun onResponse(
                    call: Call<List<MeasureModel>>,
                    response: Response<List<MeasureModel>>
                ) {
                    when {
                        response.isSuccessful -> measuresListListener.onGetMeasuresListResponseSuccess(
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
                            measuresListListener.onGetMeasuresListResponseFailure(
                                errorModel = errorModel
                            )
                        }
                    }
                }

            }
        )
    }
}