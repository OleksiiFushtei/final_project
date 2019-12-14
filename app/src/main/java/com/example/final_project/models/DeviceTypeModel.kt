package com.example.final_project.models

import com.example.final_project.models.interfaces.IDeviceTypeModel
import com.google.gson.annotations.SerializedName

data class DeviceTypeModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "typeName"
    )
    override val typeName: String
) : IDeviceTypeModel