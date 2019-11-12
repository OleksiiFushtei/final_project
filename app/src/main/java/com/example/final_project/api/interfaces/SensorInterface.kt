package com.example.final_project.api.interfaces

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
        fun onSensorSaveResponseFailure()
        fun onSensorSaveCancelled()
        fun onSensorSaveFailure()
    }

    interface SensorGetListener {
        fun onSensorGetResponseSuccess(
            sensor: SensorModel
        )

        fun onSensorGetResponseFailure()
        fun onSensorGetCancelled()
        fun onSensorGetFailure()
    }

    interface SensorDeleteListener {
        fun onSensorDeleteResponseSuccess()
        fun onSensorDeleteResponseFailure()
        fun onSensorDeleteCancelled()
        fun onSensorDeleteFailure()
    }
}