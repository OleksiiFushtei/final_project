package com.example.final_project.api.interfaces

import com.example.final_project.models.ConditionTypeModel
import com.example.final_project.models.ErrorModel

interface ConditionTypeListInterface {

    fun getTypes(
        listener: ConditionTypeListListener
    )

    interface ConditionTypeListListener {
        fun onResponseSuccess(
            list: ArrayList<ConditionTypeModel>
        )

        fun onResponseFailure(
            errorModel: ErrorModel?
        )

        fun onCancelled()
        fun onFailure()
    }

}