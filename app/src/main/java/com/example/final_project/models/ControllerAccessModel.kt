package com.example.final_project.models

import com.example.final_project.models.interfaces.IControllerAccessModel
import com.google.gson.annotations.SerializedName

data class ControllerAccessModel(
    @SerializedName("controllerId")
    override val controllerId: Int,
    @SerializedName("userName")
    override val userName: String
) : IControllerAccessModel