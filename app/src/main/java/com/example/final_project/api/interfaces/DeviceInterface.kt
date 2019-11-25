package com.example.final_project.api.interfaces

import com.example.final_project.models.DeviceModel
import com.example.final_project.models.ErrorModel

interface DeviceInterface {

    interface DeviceAddInterface {
        fun addDevice(
            deviceModel: DeviceModel,
            deviceSaveListener: DeviceSaveListener
        )
    }

    interface DeviceEditInterface {
        fun editDevice(
            deviceModel: DeviceModel,
            deviceSaveListener: DeviceSaveListener
        )
    }

    interface DeviceGetInterface {
        fun getDevice(
            id: Int,
            deviceGetListener: DeviceGetListener
        )
    }

    interface DeviceDeleteInterface {
        fun deleteDevice(
            id: Int,
            deviceDeleteListener: DeviceDeleteListener
        )
    }

    interface DeviceSaveListener {
        fun onDeviceSaveResponseSuccess()
        fun onDeviceSaveResponseFailure(
            errorModel: ErrorModel
        )

        fun onDeviceSaveCancelled()
        fun onDeviceSaveFailure()
    }

    interface DeviceGetListener {
        fun onDeviceGetResponseSuccess(
            device: DeviceModel
        )

        fun onDeviceGetResponseFailure(
            errorModel: ErrorModel
        )

        fun onDeviceGetCancelled()
        fun onDeviceGetFailure()
    }

    interface DeviceDeleteListener {
        fun onDeviceDeleteResponseSuccess()
        fun onDeviceDeleteResponseFailure(
            errorModel: ErrorModel
        )

        fun onDeviceDeleteCancelled()
        fun onDeviceDeleteFailure()
    }

}