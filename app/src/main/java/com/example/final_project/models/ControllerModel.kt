package com.example.final_project.models

import com.example.final_project.models.interfaces.IControllerModel
import com.google.gson.annotations.SerializedName

data class ControllerModel(
    @SerializedName(
        "id"
    )
    override val id: Int,
    @SerializedName(
        "name"
    )
    override val name: String,
    @SerializedName(
        "mac"
    )
    override val mac: String,
    @SerializedName(
        "status"
    )
    override val status: Boolean
) : IControllerModel