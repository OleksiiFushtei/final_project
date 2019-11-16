package com.example.final_project.models.interfaces

import com.example.final_project.models.SensorTypeModel

interface ISensorModel {
    val id: Int
    val name: String
    val pin: Int
    val status: Boolean
    var value: Int
    val sensorTypeId: Int
    val controllerId: Int
    val sensorType: SensorTypeModel?
}