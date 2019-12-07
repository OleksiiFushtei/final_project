package com.example.final_project.models.interfaces

import com.example.final_project.models.CommandModel
import com.example.final_project.models.ConditionTypeModel
import com.example.final_project.models.ControllerModel
import com.example.final_project.models.SensorModel
import java.util.*
import kotlin.collections.ArrayList

interface IScriptModel {
    val id: Int
    val name: String
    val conditionValue: Double
    val conditionTypeId: Int
    val controllerId: Int
    val lastModificationDate: Date
    val sensorId: Int
    val timeFrom: Date
    val timeTo: Date
    val delta: Double
    val repeatTimes: Int
    val completed: Boolean
    val visible: Boolean
    val priority: Int
    val commands: ArrayList<CommandModel>?
//    val sensor: SensorModel
//    val conditionType: ConditionTypeModel
//    val controller: ControllerModel
}