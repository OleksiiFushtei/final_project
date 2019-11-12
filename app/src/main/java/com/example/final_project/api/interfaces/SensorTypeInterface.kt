package com.example.final_project.api.interfaces

import com.example.final_project.models.SensorTypeModel

interface SensorTypeInterface {

    fun getSensorType(
        id: Int,
        sensorTypeListener: SensorTypeListener
    )

    interface SensorTypeListener {
        fun onSensorTypeGetResponseSuccess(sensorTypeModel: SensorTypeModel)
        fun onSensorTypeGetResponseFailure()
        fun onSensorTypeGetCancelled()
        fun onSensorTypeGetFailure()
    }
}