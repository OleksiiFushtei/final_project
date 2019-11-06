package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel

interface ControllerAddInterface{
    fun addController(
        controllerModel: ControllerModel,
        controllerAddListener: ControllerAddListener
    )

    interface ControllerAddListener {
        fun onControllerAddResponseSuccess()
        fun onControllerAddResponseFailure()
        fun onControllerAddCancelled()
        fun onControllerAddFailure()
    }
}