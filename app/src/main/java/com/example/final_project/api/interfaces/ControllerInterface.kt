package com.example.final_project.api.interfaces

import com.example.final_project.models.ControllerModel
import com.example.final_project.models.ErrorModel

interface ControllerInterface {

    interface ControllerAddInterface {
        fun addController(
            controllerModel: ControllerModel,
            controllerSaveListener: ControllerSaveListener
        )
    }

    interface ControllerEditInterface {
        fun editController(
            controllerModel: ControllerModel,
            controllerSaveListener: ControllerSaveListener
        )
    }

    interface ControllerGetInterface {
        fun getController(
            id: Int,
            controllerGetListener: ControllerGetListener
        )
    }

    interface ControllerDeleteInterface {
        fun deleteController(
            id: Int,
            controllerDeleteListener: ControllerDeleteListener
        )
    }

    interface ControllerSaveListener {
        fun onControllerSaveResponseSuccess()
        fun onControllerSaveResponseFailure(
            errorModel: ErrorModel
        )

        fun onControllerSaveCancelled()
        fun onControllerSaveFailure()
    }

    interface ControllerGetListener {
        fun onGetControllerResponseSuccess(
            controller: ControllerModel
        )

        fun onGetControllerResponseFailure(
            errorModel: ErrorModel
        )

        fun onGetControllerCancelled()
        fun onGetControllerFailure()
    }

    interface ControllerDeleteListener {
        fun onDeleteControllerResponseSuccess()
        fun onDeleteControllerResponseFailure(
            errorModel: ErrorModel
        )

        fun onDeleteControllerCancelled()
        fun onDeleteControllerFailure()
    }
}