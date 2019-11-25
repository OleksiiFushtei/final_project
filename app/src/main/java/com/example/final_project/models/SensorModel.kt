package com.example.final_project.models

import com.example.final_project.models.interfaces.ISensorModel
import com.google.gson.annotations.SerializedName

data class SensorModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "name"
    )
    override val name: String,
    @SerializedName(
        "pin"
    )
    override val pin: Int,
    @SerializedName(
        "status"
    )
    override val status: Boolean,
    @SerializedName(
        "value"
    )
    override var value: Double,
    @SerializedName(
        "sensorTypeId"
    )
    override val sensorTypeId: Int,
    @SerializedName(
        "controllerId"
    )
    override val controllerId: Int
) : ISensorModel