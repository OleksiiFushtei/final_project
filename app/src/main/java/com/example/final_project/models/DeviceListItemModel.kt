package com.example.final_project.models

import com.example.final_project.models.interfaces.IDeviceListItemModel
import com.google.gson.annotations.SerializedName

class DeviceListItemModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "usersHaveControllerId"
    )
    override val userHasControllerId: Int,
    @SerializedName(
        "deviceId"
    )
    override val deviceId: Int,
    @SerializedName(
        "userHasController"
    )
    override val userHasController: ControllerListItemModel
) : IDeviceListItemModel
