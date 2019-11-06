package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel

interface ControllerGetInterface {
    fun getController(
        id : Int,
        controllerGetListener: ControllerGetListener
    )

    interface ControllerGetListener {
        fun onGetControllerResponseSuccess(controller: ControllerModel)
        fun onGetControllerResponseFailure()
        fun onGetControllerCancelled()
        fun onGetControllerFailure()
    }
}