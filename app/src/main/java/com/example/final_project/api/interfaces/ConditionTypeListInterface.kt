package com.example.final_project.api.interfaces

import com.example.final_project.models.ConditionTypeModel

interface ConditionTypeListInterface {

    fun getTypes(
        listener: ConditionTypeListListener
    )

    interface ConditionTypeListListener {
        fun onResponseSuccess(
            list: ArrayList<ConditionTypeModel>
        )

        fun onResponseFailure()
        fun onCancelled()
        fun onFailure()
    }

}