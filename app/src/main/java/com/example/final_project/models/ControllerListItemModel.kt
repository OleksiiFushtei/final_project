package com.example.final_project.models

import com.example.final_project.models.interfaces.IControllerListItem
import com.google.gson.annotations.SerializedName

class ControllerListItemModel(
    @SerializedName(
        "isAdmin"
    )
    override val isAdmin: Boolean,
    @SerializedName(
        "controller"
    )
    override val controller: ControllerModel
) : IControllerListItem