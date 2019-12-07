package com.example.final_project.models

import com.example.final_project.models.interfaces.IDeviceConfigurationModel
import com.google.gson.annotations.SerializedName

data class DeviceConfigurationModel(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("value")
    override val value: Int,
    @SerializedName("deviceId")
    override val deviceId: Int,
    @SerializedName("measureId")
    override val measureId: Int,
    @SerializedName("device")
    override val device: DeviceModel,
    @SerializedName("measure")
    override val measure: MeasureModel
) : IDeviceConfigurationModel
