package com.example.final_project.api.interfaces

import com.example.final_project.models.ErrorModel
import com.example.final_project.models.SensorModel

interface SensorInterface {

    interface SensorAddInterface {
        fun addSensor(
            sensorModel: SensorModel,
            sensorSaveListener: SensorSaveListener
        )
    }

    interface SensorEditInterface {
        fun editSensor(
            sensorModel: SensorModel,
            sensorSaveListener: SensorSaveListener
        )
    }

    interface SensorGetInterface {
        fun getSensor(
            id: Int,
            sensorGetListener: SensorGetListener
        )
    }

    interface SensorDeleteInterface {
        fun deleteSensor(
            id: Int,
            sensorDeleteListener: SensorDeleteListener
        )
    }

    interface SensorSaveListener {
        fun onSensorSaveResponseSuccess()
        fun onSensorSaveResponseFailure(
            errorModel: ErrorModel
        )

        fun onSensorSaveCancelled()
        fun onSensorSaveFailure()
    }

    interface SensorGetListener {
        fun onSensorGetResponseSuccess(
            sensor: SensorModel
        )

        fun onSensorGetResponseFailure(
            errorModel: ErrorModel
        )

        fun onSensorGetCancelled()
        fun onSensorGetFailure()
    }

    interface SensorDeleteListener {
        fun onSensorDeleteResponseSuccess()
        fun onSensorDeleteResponseFailure(
            errorModel: ErrorModel
        )

        fun onSensorDeleteCancelled()
        fun onSensorDeleteFailure()
    }
}