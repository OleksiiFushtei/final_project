package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel

interface ControllerInterface {
    fun getControllers(
        controllerModel: ControllerModel,
        controllerListener: ControllerListener
    )

    interface ControllerListener {
        fun onGetControllersResponseSuccess(
            list: List<ControllerModel>
        )
        fun onGetControllersResponseFailure()
        fun onGetControllersCancelled()
        fun onGetControllerFailure()
    }
}