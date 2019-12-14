package com.example.final_project.api.interfaces

import com.example.final_project.models.DeviceConfigurationModel
import com.example.final_project.models.ErrorModel

interface DeviceConfigurationInterface {

    interface DeviceConfigurationAddInterface {
        fun addDeviceConfiguration(
            deviceConfigurationModel: DeviceConfigurationModel,
            deviceConfigurationSaveListener: DeviceConfigurationSaveListener
        )
    }

    interface DeviceConfigurationEditInterface {
        fun editDeviceConfiguration(
            deviceConfigurationModel: DeviceConfigurationModel,
            deviceConfigurationSaveListener: DeviceConfigurationSaveListener
        )
    }

    interface DeviceConfigurationGetInterface {
        fun getDeviceConfiguration(
            id: Int,
            deviceConfigurationGetListener: DeviceConfigurationGetListener
        )
    }

    interface DeviceConfigurationDeleteInterface {
        fun deleteDeviceConfiguration(
            id: Int,
            deviceConfigurationDeleteListener: DeviceConfigurationDeleteListener
        )
    }

    interface DeviceConfigurationSaveListener {
        fun onDeviceConfigurationSaveResponseSuccess(
            config: DeviceConfigurationModel
        )

        fun onDeviceConfigurationSaveResponseFailure(
            errorModel: ErrorModel?
        )

        fun onDeviceConfigurationSaveCancelled()
        fun onDeviceConfigurationSaveFailure()
    }

    interface DeviceConfigurationGetListener {
        fun onDeviceConfigurationGetResponseSuccess(
            deviceConfigurationModel: DeviceConfigurationModel
        )

        fun onDeviceConfigurationGetResponseFailure(
            errorModel: ErrorModel?
        )

        fun onDeviceConfigurationGetCancelled()
        fun onDeviceConfigurationGetFailure()
    }

    interface DeviceConfigurationDeleteListener {
        fun onDeviceConfigurationDeleteResponseSuccess()
        fun onDeviceConfigurationDeleteResponseFailure(
            errorModel: ErrorModel?
        )

        fun onDeviceConfigurationDeleteCancelled()
        fun onDeviceConfigurationDeleteFailure()
    }
}