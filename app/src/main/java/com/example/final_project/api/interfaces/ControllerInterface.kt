package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel

interface ControllerInterface {

    interface ControllerAddInterface {
        fun addController(
            controllerModel: ControllerModel,
            controllerAddListener: ControllerAddListener
        )
    }

    interface ControllerGetInterface {
        fun getController(
            controllerGetListener: ControllerGetListener
        )
    }

    interface ControllerAddListener {
        fun onControllerAddResponseSuccess()
        fun onControllerAddResponseFailure()
        fun onControllerAddCancelled()
        fun onControllerAddFailure()
    }

    interface ControllerGetListener {
        fun onGetControllerResponseSuccess(
            controller: ControllerModel
        )

        fun onGetControllerResponseFailure()
        fun onGetControllerCancelled()
        fun onGetControllerFailure()
    }
}