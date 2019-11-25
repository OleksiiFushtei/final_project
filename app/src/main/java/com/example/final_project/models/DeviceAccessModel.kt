package com.example.final_project.models

import com.example.final_project.models.interfaces.IDeviceAccessModel
import com.google.gson.annotations.SerializedName

data class DeviceAccessModel(
    @SerializedName("deviceId")
    override val deviceId: Int,
    @SerializedName("userName")
    override val userName: String
) : IDeviceAccessModel