package com.example.final_project.api.interfaces

import com.example.final_project.models.DeviceModel
import com.example.final_project.models.ErrorModel

interface DevicesListInterface {
    fun getListOfDevices(
        id: Int,
        virtual: Boolean,
        devicesListListener: DevicesListListener
    )

    interface DevicesListListener {
        fun onGetDevicesListResponseSuccess(
            list: ArrayList<DeviceModel>
        )

        fun onGetDevicesListResponseFailure(
            errorModel: ErrorModel
        )

        fun onGetDevicesListCancelled()
        fun onGetDevicesListFailure()
    }
}