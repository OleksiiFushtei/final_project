package com.example.final_project.models

import com.example.final_project.models.interfaces.IMeasureModel
import com.google.gson.annotations.SerializedName

data class MeasureModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "measureName"
    )
    override val measureName: String,
    @SerializedName(
        "deviceTypeId"
    )
    override val deviceTypeId: Int
) : IMeasureModel
