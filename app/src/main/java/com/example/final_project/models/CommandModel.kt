package com.example.final_project.models

import com.example.final_project.models.interfaces.ICommandModel
import com.google.gson.annotations.SerializedName

data class CommandModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "scriptId"
    )
    override val scriptId: Int,
    @SerializedName(
        "timeSpan"
    )
    override val timeSpan: String,
    @SerializedName(
        "deviceConfigurationId"
    )
    override val deviceConfigurationId: Int,
    @SerializedName(
        "name"
    )
    override val name: String,
    @SerializedName(
        "end"
    )
    override val end: Boolean,
    @SerializedName(
        "deviceConfiguration"
    )
    override val deviceConfiguration: DeviceConfigurationModel
) : ICommandModel