package com.example.final_project.models.interfaces

import java.util.*

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
}