package com.example.final_project.models

import com.example.final_project.models.interfaces.IScriptModel
import com.google.gson.annotations.SerializedName

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
    override val conditionValue: Double?,
    @SerializedName(
        "conditionTypeId"
    )
    override val conditionTypeId: Int?,
    @SerializedName(
        "controllerId"
    )
    override val controllerId: Int,
    @SerializedName(
        "sensorId"
    )
    override val sensorId: Int?,
    @SerializedName(
        "timeFrom"
    )
    override val timeFrom: String,
    @SerializedName(
        "timeTo"
    )
    override val timeTo: String?,
    @SerializedName(
        "delta"
    )
    override val delta: Double,
    @SerializedName(
        "repeatTimes"
    )
    override val repeatTimes: Int,
    @SerializedName(
        "status"
    )
    override val status: Boolean,
    @SerializedName(
        "priority"
    )
    override val priority: Int,
    @SerializedName(
        "sensor"
    )
    override val sensor: SensorModel,
    @SerializedName(
        "conditionType"
    )
    override val conditionType: ConditionTypeModel
) : IScriptModel