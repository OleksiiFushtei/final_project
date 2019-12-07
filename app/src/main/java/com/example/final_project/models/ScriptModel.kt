package com.example.final_project.models

import com.example.final_project.models.interfaces.IScriptModel
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class ScriptModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "name"
    )
    override val name: String,
    @SerializedName(
        "conditionValue"
    )
    override val conditionValue: Double,
    @SerializedName(
        "conditionTypeId"
    )
    override val conditionTypeId: Int,
    @SerializedName(
        "controllerId"
    )
    override val controllerId: Int,
    @SerializedName(
        "lastModificationDate"
    )
    override val lastModificationDate: Date,
    @SerializedName(
        "sensorId"
    )
    override val sensorId: Int,
    @SerializedName(
        "timeFrom"
    )
    override val timeFrom: Date,
    @SerializedName(
        "timeTo"
    )
    override val timeTo: Date,
    @SerializedName(
        "delta"
    )
    override val delta: Double,
    @SerializedName(
        "repeatTimes"
    )
    override val repeatTimes: Int,
    @SerializedName(
        "complited"
    )
    override val completed: Boolean,
    @SerializedName(
        "visible"
    )
    override val visible: Boolean,
    @SerializedName(
        "priority"
    )
    override val priority: Int,
    @SerializedName(
        "commands"
    )
    override val commands: ArrayList<CommandModel>?
//    @SerializedName(
//        "sensor"
//    )
//    override val sensor: SensorModel,
//    @SerializedName(
//        "conditionType"
//    )
//    override val conditionType: ConditionTypeModel,
//    @SerializedName(
//        "controller"
//    )
//    override val controller: ControllerModel
) : IScriptModel