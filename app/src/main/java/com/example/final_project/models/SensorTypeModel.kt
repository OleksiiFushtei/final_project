package com.example.final_project.models

import com.example.final_project.models.interfaces.ISensorTypeModel
import com.google.gson.annotations.SerializedName

data class SensorTypeModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "typeName"
    )
    override val typeName: String
) : ISensorTypeModel