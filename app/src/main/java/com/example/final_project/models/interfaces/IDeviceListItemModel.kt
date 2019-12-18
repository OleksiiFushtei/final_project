package com.example.final_project.models.interfaces

import com.example.final_project.models.ControllerListItemModel

interface IDeviceListItemModel {
    val id: Int
    val userHasControllerId: Int
    val deviceId: Int
    val userHasController: ControllerListItemModel
}