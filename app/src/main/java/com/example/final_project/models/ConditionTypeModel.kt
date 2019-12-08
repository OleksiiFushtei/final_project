package com.example.final_project.models

import com.example.final_project.models.interfaces.IConditionTypeModel
import com.google.gson.annotations.SerializedName

data class ConditionTypeModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "type"
    )
    override val type: String
) : IConditionTypeModel
