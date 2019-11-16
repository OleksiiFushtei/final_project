package com.example.final_project.api.interfaces

import com.example.final_project.models.SensorTypeModel

interface SensorTypeInterface {

    fun getSensorTypes(
        sensorTypeListener: SensorTypesListener
    )

    interface SensorTypesListener {
        fun onSensorTypesGetResponseSuccess(list: ArrayList<SensorTypeModel>)
        fun onSensorTypesGetResponseFailure()
        fun onSensorTypesGetCancelled()
        fun onSensorTypesGetFailure()
    }
}