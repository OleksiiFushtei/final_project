package com.example.final_project.models.interfaces

import com.example.final_project.models.DeviceConfigurationModel
import com.example.final_project.models.DeviceTypeModel

interface IMeasureModel {
    val id: Int
    val measureName: String
    val deviceTypeId: Int
    val deviceConfigurations: ArrayList<DeviceConfigurationModel>
    val deviceType: DeviceTypeModel
}
