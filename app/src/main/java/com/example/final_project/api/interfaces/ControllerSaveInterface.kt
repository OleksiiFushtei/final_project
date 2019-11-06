package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel

interface ControllerSaveInterface {
    fun saveController(
        controllerModel: ControllerModel,
        controllerSaveListener: ControllerSaveListener
    )

    interface ControllerSaveListener {
        fun onSaveControllerResponseSuccess()
        fun onSaveControllerResponseFailure()
        fun onSaveControllerCancelled()
        fun onSaveControllerFailure()
    }
}