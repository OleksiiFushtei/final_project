package com.example.final_project.models.interfaces

import com.example.final_project.models.DeviceConfigurationModel

interface ICommandModel {
    val id: Int
    val scriptId: Int
    val timeSpan: String
    val deviceConfigurationId: Int
    val name: String
    val end: Boolean
    val deviceConfiguration: DeviceConfigurationModel
}