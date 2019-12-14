package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.MeasureModel

interface MeasuresListInterface {

    fun getListOfMeasures(
        id: Int,
        measuresListListener: MeasuresListListener
    )

    interface MeasuresListListener {
        fun onGetMeasuresListResponseSuccess(
            list: ArrayList<MeasureModel>
        )

        fun onGetMeasuresListResponseFailure(
            errorModel: ErrorModel?
        )

        fun onGetMeasuresListCancelled()
        fun onGetMeasuresListFailure()
    }
}