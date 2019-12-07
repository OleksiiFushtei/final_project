package com.example.final_project.models.interfaces

import com.example.final_project.models.DeviceModel
import com.example.final_project.models.MeasureModel

interface IDeviceConfigurationModel {
    val id: Int
    val value: Int
    val deviceId: Int
    val measureId: Int
    val device: DeviceModel
    val measure: MeasureModel
}