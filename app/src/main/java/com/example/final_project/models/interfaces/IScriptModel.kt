package com.example.final_project.models.interfaces

import com.example.final_project.models.ConditionTypeModel
import com.example.final_project.models.SensorModel

interface IScriptModel {
    val id: Int
    val name: String
    val conditionValue: Double?
    val conditionTypeId: Int?
    val sensorId: Int?
    val status: Boolean
    val controllerId: Int
    val timeFrom: String
    val timeTo: String?
    val delta: Double
    val repeatTimes: Int
    val priority: Int
    val sensor: SensorModel?
    val conditionType: ConditionTypeModel?
}