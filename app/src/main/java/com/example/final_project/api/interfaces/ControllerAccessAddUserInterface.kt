package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerAccessModel
import com.example.final_project.models.ErrorModel

interface ControllerAccessAddUserInterface {

    fun addUser(
        controllerAccessModel: ControllerAccessModel,
        addUserToListListener: AddUserToListListener
    )

    interface AddUserToListListener {
        fun onAddUserToListResponseSuccess()
        fun onAddUserToListResponseFailure(
            errorModel: ErrorModel
        )

        fun onAddUserToListCancelled()
        fun onAddUserToListFailure()
    }
}