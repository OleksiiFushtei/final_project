package com.example.final_project.api.interfaces

import com.example.final_project.models.SensorModel

interface SensorsListInterface {
    fun getListOfSensors(
        id: Int,
        sensorsListListener: SensorsListListener
    )

    interface SensorsListListener {
        fun onGetSensorsListResponseSuccess(
            list: ArrayList<SensorModel>
        )

        fun onGetSensorsListResponseFailure()
        fun onGetSensorsListCancelled()
        fun onGetSensorsListFailure()
    }
}