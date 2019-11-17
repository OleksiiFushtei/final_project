package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerListItemModel
import com.example.final_project.models.ErrorModel

interface ControllersListInterface {
    fun getListOfController(
        controllerListListener: ControllersListListener
    )

    interface ControllersListListener {
        fun onGetControllersListResponseSuccess(
            list: ArrayList<ControllerListItemModel>
        )

        fun onGetControllersListResponseFailure(
            errorModel: ErrorModel
        )

        fun onGetControllersListCancelled()
        fun onGetControllerListFailure()
    }

}