package com.example.final_project.models.interfaces

import com.example.final_project.models.DeviceTypeModel

interface IDeviceModel {
    val id: Int
    val name: String
    val pin: Int?
    val mac: String?
    val status: Boolean
    val controllerId: Int
    val deviceTypeId: Int
    val deviceType: DeviceTypeModel?
}