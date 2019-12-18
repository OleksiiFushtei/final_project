package com.example.final_project.api.interfaces

import com.example.final_project.models.DeviceAccessModel
import com.example.final_project.models.ErrorModel

interface DeviceAccessAddUserInterface {

    fun addUser(
        deviceAccessModel: DeviceAccessModel,
        addUserToListListener: AddUserToListListener
    )

    interface AddUserToListListener {
        fun onAddUserToListResponseSuccess()
        fun onAddUserToListResponseFailure(
            errorModel: ErrorModel?
        )

        fun onAddUserToListCancelled()
        fun onAddUserToListFailure()
    }
}