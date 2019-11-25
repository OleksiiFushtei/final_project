package com.example.final_project.models

import com.example.final_project.models.interfaces.IDeviceModel

data class DeviceModel(
    override val id: Int,
    override val name: String,
    override val pin: Int?,
    override val mac: String?,
    override val status: Boolean,
    override val controllerId: Int,
    override val deviceTypeId: Int,
    override val deviceType: DeviceTypeModel?
) : IDeviceModel